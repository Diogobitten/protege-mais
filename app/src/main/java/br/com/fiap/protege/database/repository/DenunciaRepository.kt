package br.com.fiap.protege.database.repository

import android.content.Context
import br.com.fiap.protege.database.dao.DenunciaDB
import br.com.fiap.protege.model.Denuncia

class DenunciaRepository (context: Context) {

    var db = DenunciaDB.getDatabase(context).denunciaDao()

    fun salvar(denuncia: Denuncia):Long{
        return db.salvar(denuncia = denuncia)
    }

    fun atualizar(denuncia: Denuncia):Int{
        return db.atualizar(denuncia = denuncia)
    }

    fun excluir(denuncia: Denuncia):Int{
        return db.excluir(denuncia = denuncia)
    }

    fun listar():List<Denuncia>{
        return db.listar()
    }


}