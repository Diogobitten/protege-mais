package br.com.fiap.protege.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tb_denuncia")
data class Denuncia(
    @PrimaryKey (autoGenerate = true) var id:Long=0,
    var descricao:String = "",
    var categoria:String="",
    var localizacao:String=""
)
