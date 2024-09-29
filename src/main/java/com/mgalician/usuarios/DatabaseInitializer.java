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
        cuenta1.setNumeroCuenta(1275631);
        cuenta1.setIngresos(37450.50f);
        

        cuentaRepository.save(cuenta1);
        
        DireccionEntity direccion1 = new DireccionEntity();
        direccion1.setCodigoPostal("13610");
        direccion1.setEstado("CDMX");

        DireccionEntity direccion2 = new DireccionEntity();
        direccion2.setCodigoPostal("01980");
        direccion2.setEstado("Estado de México");

        direccionRepository.save(direccion1);
        direccionRepository.save(direccion2);

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
