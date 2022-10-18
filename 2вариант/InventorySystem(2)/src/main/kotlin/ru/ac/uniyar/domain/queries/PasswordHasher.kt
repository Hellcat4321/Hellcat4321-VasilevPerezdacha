package ru.ac.uniyar.domain.queries

fun hashPassword(password: String, salt: String): String{
    val saltedPassword = password + salt
    return Checksum(saltedPassword.toByteArray()).toString()

//    var generatePassword = "password" + "x-AS-6spMC2Jygi_JoKoXyZbVmBmsmKPEbA4Bau3TVSbB3QPKYWkcDnms5aTDyGz39UH8ol00ADNP2h2AnCWJg"
//    generatePassword = Checksum(generatePassword.toByteArray()).toString()
//    var tmp = 1
//    return "hfhfhdf"
}
