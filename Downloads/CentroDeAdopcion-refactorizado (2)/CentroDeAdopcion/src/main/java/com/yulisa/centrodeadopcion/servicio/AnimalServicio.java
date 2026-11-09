package com.yulisa.centrodeadopcion.servicio;

import com.yulisa.centrodeadopcion.dto.AnimalDTO;
import com.yulisa.centrodeadopcion.dto.RegistroAnimalDTO;
import com.yulisa.centrodeadopcion.modelo.Animal;
import com.yulisa.centrodeadopcion.modelo.Cliente;
import com.yulisa.centrodeadopcion.modelo.DatosAnimal;
import com.yulisa.centrodeadopcion.repositorio.RepositorioAnimales;
import com.yulisa.centrodeadopcion.repositorio.RepositorioClientes;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/** Registro, consulta y adopción de animales. */
public class AnimalServicio {

    private static final int EDAD_MINIMA = 0;
    private static final int EDAD_MAXIMA = 40;

    private final RepositorioAnimales animales;
    private final RepositorioClientes clientes;
    private final Clock reloj;

    public AnimalServicio(RepositorioAnimales animales, RepositorioClientes clientes) {
        this(animales, clientes, Clock.systemDefaultZone());
    }

    /** El reloj se inyecta para poder probar la validación de fechas. */
    public AnimalServicio(RepositorioAnimales animales, RepositorioClientes clientes, Clock reloj) {
        this.animales = Objects.requireNonNull(animales);
        this.clientes = Objects.requireNonNull(clientes);
        this.reloj = Objects.requireNonNull(reloj);
    }

    public AnimalDTO registrar(RegistroAnimalDTO d) throws ValidacionException {
        List<String> errores = new ArrayList<>();
        if (d.tipo() == null) {
            errores.add("Seleccione el tipo de animal.");
        }
        Reglas.requerido(d.nombre(), "nombre", errores);
        Reglas.requerido(d.raza(), "raza", errores);
        int edad = Reglas.edadEnRango(d.edad(), EDAD_MINIMA, EDAD_MAXIMA, errores);
        if (d.fechaIngreso() == null) {
            errores.add("La fecha de ingreso es obligatoria.");
        } else if (d.fechaIngreso().isAfter(LocalDate.now(reloj))) {
            errores.add("La fecha de ingreso no puede ser futura.");
        }
        Reglas.lanzarSiHayErrores(errores);

        DatosAnimal datos = new DatosAnimal(
                d.nombre().trim(), edad, d.raza().trim(), d.fechaIngreso(),
                d.descripcion() == null ? "" : d.descripcion().trim(),
                d.entrenado(), d.protector(), d.tieneDiscapacidad(), d.cuidadosEspeciales());
        Animal animal = d.tipo().crear(datos);
        animales.guardar(animal);
        return AnimalDTO.desde(animal);
    }

    public List<AnimalDTO> listarTodos() {
        return aDTO(animales.listar());
    }

    public List<AnimalDTO> listarDisponibles() {
        return aDTO(animales.listarDisponibles());
    }

    /**
     * Adopta por id (nunca por nombre). Comprueba que el animal siga disponible
     * y que el cliente esté capacitado; solo entonces cambia el estado y guarda.
     */
    public AnimalDTO adoptar(UUID idAnimal, String nombreUsuarioCliente) throws ValidacionException {
        if (idAnimal == null) {
            throw new ValidacionException("Seleccione un animal de la tabla.");
        }
        Animal animal = animales.buscarPorId(idAnimal)
                .orElseThrow(() -> new ValidacionException("El animal seleccionado ya no existe."));
        if (animal.isAdoptado()) {
            throw new ValidacionException(animal.getNombre() + " ya fue adoptado.");
        }
        Cliente cliente = clientes.buscarPorId(nombreUsuarioCliente)
                .orElseThrow(() -> new ValidacionException("Inicie sesión como cliente para adoptar."));
        if (!cliente.estaCapacitado()) {
            throw new ValidacionException(
                    "Para adoptar se requiere contar con ingresos propios y vivienda propia.");
        }
        animal.marcarComoAdoptado();
        animales.guardar(animal);
        return AnimalDTO.desde(animal);
    }

    private static List<AnimalDTO> aDTO(List<Animal> lista) {
        return lista.stream()
                .sorted(Comparator.comparing(Animal::getFechaIngreso).thenComparing(Animal::getNombre))
                .map(AnimalDTO::desde)
                .toList();
    }
}
