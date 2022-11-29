import controllers.AlumnoController
import controllers.CursoController
import db.HibernateManager
import db.getAlumnos
import db.getCursos
import mu.KotlinLogging
import util.ApplicationProperties

val logger = KotlinLogging.logger {  }

fun main() {
    initDB()

    val alumnos = getAlumnos()
    val cursos = getCursos()

    alumnos.forEach { AlumnoController.save(it) }
    cursos.forEach { CursoController.save(it) }


}

fun initDB() {
    val properties = ApplicationProperties()
    logger.debug { "Reading properties file ${properties.readProperty("app.title")}" }
    HibernateManager.open()
    HibernateManager.close()
}