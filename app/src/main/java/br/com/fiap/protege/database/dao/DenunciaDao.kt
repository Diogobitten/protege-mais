package br.com.fiap.protege.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.protege.model.Denuncia


@Dao
interface DenunciaDao {
    @Insert
    fun salvar(denuncia: Denuncia):Long
    @Delete
    fun excluir(denuncia: Denuncia):Int
    @Update
    fun atualizar(denuncia: Denuncia):Int
    @Query("SELECT * FROM tb_denuncia ORDER BY categoria ASC")
    fun listar():List<Denuncia>
}