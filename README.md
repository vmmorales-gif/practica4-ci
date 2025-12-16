# MUSICA (Maven)

Proyecto convertido desde Eclipse a una estructura estándar Maven.

## Estructura
- Código de producción: `src/main/java`
- Tests (JUnit 5): `src/test/java`

## Comandos

### Ejecutar tests
```bash
mvn clean test
```

### Empaquetar (JAR)
```bash
mvn clean package
```

## Nota sobre los tests
En `ServicioMusicaTests` hay varios tests marcados como **fallos intencionales**.
Esto viene genial para la práctica de Integración Continua porque te permite:
- hacer una ejecución **fallida** (pipeline en rojo)
- corregir/comentar esos tests y repetir para una ejecución **exitosa** (pipeline en verde)
