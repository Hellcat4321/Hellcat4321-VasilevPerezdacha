package ru.ac.uniyar.domain.storage

data class Settings(
    private val salt: String = "x-AS-6spMC2Jygi_JoKoXyZbVmBmsmKPEbA4Bau3TVSbB3QPKYWkcDnms5aTDyGz39UH8ol00ADNP2h2AnCWJg"
){
    fun getSalt() : String{
        return salt
    }
}
