package com.mgalician.usuarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mgalician.usuarios.model.entity.CuentaEntity;
import com.mgalician.usuarios.model.entity.DireccionEntity;
import com.mgalician.usuarios.model.entity.UsuarioEntity;
import com.mgalician.usuarios.repository.CuentaRepository;
import com.mgalician.usuarios.repository.DireccionRepository;
import com.mgalician.usuarios.repository.UsuarioRepository;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final CuentaRepository cuentaRepository;
    private final DireccionRepository direccionRepository;

    public DatabaseInitializer(UsuarioRepository usuarioRepository, 
    CuentaRepository cuentaRepository,
    DireccionRepository direccionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cuentaRepository = cuentaRepository;
        this.direccionRepository = direccionRepository;
    }

    @Override
    public void run(String... args) throws ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        CuentaEntity cuenta1 = new CuentaEntity();
        cuenta1.setNumeroCuenta(1212341234);
        cuenta1.setIngresos(37450.50f);
        
        CuentaEntity cuenta2 = new CuentaEntity();
        cuenta2.setNumeroCuenta(1212341715);
        cuenta2.setIngresos(21000.00f);

        cuentaRepository.save(cuenta1);
        cuentaRepository.save(cuenta2);
        
        DireccionEntity direccion1 = new DireccionEntity();
        direccion1.setCodigoPostal("136101");
        direccion1.setEstado("CDMX");

        DireccionEntity direccion2 = new DireccionEntity();
        direccion2.setCodigoPostal("019802");
        direccion2.setEstado("Estado de México");

        DireccionEntity direccion3 = new DireccionEntity();
        direccion3.setCodigoPostal("239365");
        direccion3.setEstado("Guadalajara");

        direccionRepository.save(direccion1);
        direccionRepository.save(direccion2);
        direccionRepository.save(direccion3);

        UsuarioEntity usuario1 = new UsuarioEntity();
        usuario1.setNombre("Macario");
        usuario1.setApellidoPaterno("Galicia");
        usuario1.setApellidoMaterno("Negrete");
        usuario1.setFechaNacimiento(formato.parse("17/04/1997"));
        usuario1.setCuenta(cuenta1);
        usuario1.setDireccion(direccion1);

        UsuarioEntity usuario2 = new UsuarioEntity();
        usuario2.setNombre("Jesica Guadalupe");
        usuario2.setApellidoPaterno("Avendaño");
        usuario2.setApellidoMaterno("Ojeda");
        usuario2.setFechaNacimiento(formato.parse("07/10/1996"));
        usuario2.setCuenta(cuenta2);
        usuario2.setDireccion(direccion2);

        UsuarioEntity usuario3 = new UsuarioEntity();
        usuario3.setNombre("Miguel Angel");
        usuario3.setApellidoPaterno("Cortes");
        usuario3.setApellidoMaterno("Trinidad");
        usuario3.setFechaNacimiento(formato.parse("29/05/1985"));

        usuarioRepository.save(usuario1);
        usuarioRepository.save(usuario2);
        usuarioRepository.save(usuario3);

        System.out.println("Datos insertados en la base de datos al inicializar la aplicación.");
    }
}
