-- Ajustar el contador de IDs para que no colision con los IDs anteriores
UPDATE USUARIO_GEN set GEN_VAL=100 WHERE GEN_NAME='USUARIO_GEN';


-- Administrador inicial con login "admin" y contraseña "admin"
INSERT INTO `ADMINISTRADOR` (`ID`,`FECHAALTA`,`LOGIN`,`NOMBRE`,
                             `PASSWORD`,`TIPO_USUARIO`,`ULTIMOACCESO`)
   VALUES (1,'2016-11-11 01:04:42','admin','Administrador inicial',
           'ggm44T97GWJ6Rryx3do4yvl1bZ+gmUfG','ADMINISTRADOR','2016-11-11 01:04:42');

-- Medico con dni "11111111A", num. colegiado "11111" y contraseña "11111"
INSERT INTO `CENTROSALUD` (`ID`,`NOMBRE`,`TELEFONO`,`CODIGOPOSTAL`,
                           `DOMICILIO`,`LOCALIDAD`,`PROVINCIA`)
   VALUES (1,'Centro salud pepe','988888888','12345',
           'C/. Pepe, nº 3','Ourense','Ourense');
INSERT INTO `MEDICO` (`ID`,`APELLIDOS`,`DNI`,`EMAIL`,`FECHAALTA`,`NOMBRE`,
                      `NUMEROCOLEGIADO`,`PASSWORD`,`TELEFONO`,`TIPO_USUARIO`,
                      `ULTIMOACCESO`,`CENTROSALUD_ID`)
   VALUES (2,'Gomez Gomez','11111111A','a@a.com','2016-11-11 01:04:42','Antonio',
           '11111','lUTQ2zg2voe4Z5OqpITFIjcBziNH10d6','988123456','MEDICO',
           '2016-11-11 01:04:42',1);

-- Paciente con dni "22222222B", num. tarjeta sanitaria "2222222222", num seg. social "2222222222222" y contraseña "22222"
INSERT INTO `PACIENTE` (`ID`,`APELLIDOS`,`DNI`,`EMAIL`,`FECHAALTA`,
                        `NOMBRE`,`NUMEROSEGURIDADSOCIAL`,`NUMEROTARJETASANITARIA`,
                        `PASSWORD`,`TELEFONO`,`TIPO_USUARIO`,`ULTIMOACCESO`,
                        `CODIGOPOSTAL`,`DOMICILIO`,`LOCALIDAD`,`PROVINCIA`,`MEDICO_ID`)
   VALUES (3,'Benito Carmona','22222222B','b@b.com','2016-11-11 01:04:42',
           'Ana','2222222222222','2222222222',
           'auJIfVxFAN0IKkDVovGmzfUENiABIC5g','981123456','PACIENTE','2016-11-11 01:04:42',
           '12345','C/. jander clander, nº 2, 4º N','Coruña','Coruña',2),
   (101, 'Alvarez Martinez', '22222222A', 'ant@ent', '2017-01-11 16:42:01', 'Horacio', '1234567890123', '1234567890', 'Aevoaydpbl86M0/8fduTCcCILJQlhcVn', '666554434', 'PACIENTE', '2017-01-11 16:42:01', '32004', 'c/ Norte', 'Ourense', 'Ourense', 2);

-- Cita con fecha "2016-11-23" y hora "09:00:00"
INSERT INTO `CITA`(`ID`,`DURACION`,`ESTADO`,`FECHA`,`HORA`,`MEDICO_ID`,`PACIENTE_ID`)
  VALUES (1, 15, 'PLANIFICADA', '2016-11-23', '09:00:00', 2, 3),
(2, 15, 'PLANIFICADA', '2017-01-09', '11:00:00', 2, 3),
(3, 15, 'PLANIFICADA', '2017-01-09', '01:00:00', 2, 3),
(4, 15, 'PLANIFICADA', '2017-01-15', '11:00:00', 2, 3),
(5, 15, 'PLANIFICADA', '2017-01-15', '12:00:00', 2, 101),
(6, 15, 'PLANIFICADA', '2017-01-15', '01:00:00', 2, 3),
(7, 15, 'COMPLETADA', '2017-01-15', '10:00:00', 2, 101);


-- Farmacia con nif "33333333C" y contraseña "33333"
INSERT INTO `FARMACIA`(`ID`,`FECHAALTA`,`NIF`,`NOMBREFARMACIA`,
                       `PASSWORD`,`TIPO_USUARIO`,`ULTIMOACCESO`,
                       `CODIGOPOSTAL`,`DOMICILIO`,`LOCALIDAD`,`PROVINCIA`)
   VALUES (4,'2016-11-11 01:04:42','33333333C','Farmacia de prueba',
           '/QpUw+ZRH3ndoNb3N4gRpT5cz0C7pT9v','FARMACIA','2016-11-11 01:04:42',
           '12345','C/. Farmacia, nº 2, 4º N','Coruña','Coruña');

--
-- Volcado de datos para la tabla `medicamento`
--

INSERT INTO `medicamento` (`ID`, `FABRICANTE`, `FAMILIA`, `NOMBRE`, `NUMERODOSIS`, `PRINCIPIOACTIVO`) VALUES
(1, 'Bayer', 'acsasa', 'Aspirina', 6, 'acetil'),
(2, 'Bayer', 'sasasas', 'Paracetamol', 12, 'asdasdasd'),
(3, 'Jaja', '2wewq', 'Dalsy', 10, 'adoasdas'),
(4, 'sadsads', 'sdsfrfs', 'Ibuprofeno', 20, 'sadeeda');

--
-- Volcado de datos para la tabla `prescripcion`
--

INSERT INTO `prescripcion` (`ID`, `DOSIS`, `FECHAFIN`, `FECHAINICIO`, `INDICACIONES`, `MEDICAMENTO_ID`, `MEDICO_ID`, `PACIENTE_ID`) VALUES
(1, 12, '2017-01-11', '2017-01-09', 'tomatelo', 4, 2, 3),
(2, 10, '2017-01-17', '2017-01-11', 'tomate las pastillas', 1, 2, 3),
(3, 23, '2017-01-26', '2017-01-11', 'aaa', 4, 2, 101),
(4, 23, '2017-01-17', '2017-01-11', 'bbb', 3, 2, 3),
(5, 10, '2017-01-25', '2017-01-11', 'cccc', 2, 2, 3),
(6, 12, '2017-01-22', '2017-01-15', 'ddddd', 1, 2, 101);

-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `receta`
--

INSERT INTO `receta` (`ID`, `CANTIDAD`, `ESTADORECETA`, `FINVALIDEZ`, `INICIOVALIDEZ`, `FARMACIADISPENSADORA_ID`, `PRESCRIPCION_ID`) VALUES
(1, 1, 'SERVIDA', '2017-01-11', '2017-01-09', 4, 1),
(2, 3, 'SERVIDA', '2017-01-17', '2017-01-11', 4, 2),
(3, 2, 'GENERADA', '2017-01-26', '2017-01-11', 4, 3),
(4, 1, 'ANULADA', '2017-01-17', '2017-01-11', 4, 4),
(5, 1, 'GENERADA', '2017-01-25', '2017-01-11', 4, 5),
(6, 1, 'GENERADA', '2017-01-29', '2017-01-08', NULL, 6),
(7, 1, 'GENERADA', '2017-01-29', '2017-01-08', NULL, 6),
(8, 1, 'GENERADA', '2017-01-29', '2017-01-08', NULL, 6),
(9, 1, 'GENERADA', '2017-01-29', '2017-01-08', NULL, 6),
(10, 1, 'GENERADA', '2017-01-29', '2017-01-08', NULL, 6),
(11, 1, 'GENERADA', '2017-01-29', '2017-01-08', NULL, 6),
(12, 1, 'GENERADA', '2017-01-29', '2017-01-08', NULL, 6),
(13, 1, 'GENERADA', '2017-01-29', '2017-01-08', NULL, 6),
(14, 1, 'GENERADA', '2017-01-29', '2017-01-08', NULL, 6),
(15, 1, 'GENERADA', '2017-01-29', '2017-01-08', NULL, 6),
(16, 1, 'GENERADA', '2017-01-29', '2017-01-08', NULL, 6),
(17, 1, 'GENERADA', '2017-01-29', '2017-01-08', NULL, 6),
(18, 1, 'GENERADA', '2017-01-29', '2017-01-08', NULL, 6),
(19, 1, 'GENERADA', '2017-01-29', '2017-01-08', NULL, 6);
