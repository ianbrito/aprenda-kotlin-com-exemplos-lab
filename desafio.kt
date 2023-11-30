// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

class UsuarioJaMatriculadoException(message: String) : Throwable(message)

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(val id: Int, val nome: String) {
     override fun equals(other: Any?) = other is Usuario && other.id == this.id
}

data class ConteudoEducacional(val nome: String, val duracao: Int = 1)

data class Formacao(val nome: String, val nivel: Nivel, val conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()
    
    fun matricular(usuario: Usuario) {
        
        if (inscritos.find { it.equals(usuario) } != null)
        	throw UsuarioJaMatriculadoException("Usuário ${usuario.nome} já matriculado")
        
        inscritos.add(usuario)
    }
}

fun main() {
    
    val conteudos = listOf(
        ConteudoEducacional("Trabalhando em Equipes Ágeis", 2),
        ConteudoEducacional("Versionamento de Código com Git e GitHub", 4),
    )
    
    val formacao = Formacao(nome = "Desenvolvimento Backend com Kotlin", nivel = Nivel.BASICO, conteudos = conteudos)
    
    val usuarios: List<Usuario> = listOf(Usuario(id = 1, nome = "Ian"), Usuario(id = 2, nome = "João"), Usuario(id = 1, nome = "Pedro"))
    
    for (usuario in usuarios) {
        try {
            formacao.matricular(usuario)
            println("Usuário ${usuario.nome} matriculado na formação ${formacao.nome}")
        } catch (e: UsuarioJaMatriculadoException) {
            println(e.message)
        }    
    }
    
    println(formacao)
}