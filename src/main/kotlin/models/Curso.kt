package models

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Cursos")
@NamedQuery(name = "Curso.findAll", query = "select c from Curso c")
class Curso() {
    @Id
    //@GeneratedValue
    //@GenericGenerator( // OPCIONAL
    //    name = "UUID",
    //    strategy = "org.hibernate.id.UUIDGenerator"
    //)
    @Column(name = "id")
    //@Type(type = "uuid-char") // OPCIONAL
    lateinit var id: UUID

    lateinit var nombre: String

    @OneToMany(mappedBy = "curso", orphanRemoval = true,
        cascade = [CascadeType.PERSIST], fetch = FetchType.EAGER)
    lateinit var alumnos: MutableList<Alumno>

    @Column(name = "generated_at")
    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @CreationTimestamp
    lateinit var generatedAt: LocalDateTime

    constructor(
        id: UUID?,
        nombre: String,
        alumnos: MutableList<Alumno>?
    ): this() {
        this.id = id ?: UUID.randomUUID()
        this.nombre = nombre
        this.alumnos = alumnos ?: mutableListOf()
        this.generatedAt = LocalDateTime.now()
    }
}