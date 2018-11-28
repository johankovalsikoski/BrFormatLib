package johan.kovalsikoski.brformat

class BrFormat {

    fun isValidCreditCardLenght (cardNumber: String, lenght: Int): Boolean = cardNumber.trim().length == lenght

    fun formatCreditCard (cardNumber: String): String {

        var exitString = ""

        for(i in cardNumber.indices){
            if(i != 0){
                exitString += if(i % 4 == 0)
                    " ${cardNumber[i]}"
                else
                    cardNumber[i]
            } else {
                exitString = "${cardNumber[i]}"
            }
        }

        return exitString
    }

    /*
    A solução apresentada para validar CPF e CNPJ foi desenvolvida e publicada por Carlos Caldas
    https://www.vivaolinux.com.br/script/Codigo-para-validar-CPF-e-CNPJ-otimizado
    */

    private val pesoCPF = intArrayOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2)
    private val pesoCNPJ = intArrayOf(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2)

    private fun calcularDigito(str: String, peso: IntArray): Int {
        var soma = 0
        var indice = str.length - 1
        var digito: Int
        while (indice >= 0) {
            digito = Integer.parseInt(str.substring(indice, indice + 1))
            soma += digito * peso[peso.size - str.length + indice]
            indice--
        }
        soma = 11 - soma % 11
        return if (soma > 9) 0 else soma
    }

    fun isValidCPF(cpf: String?): Boolean {
        if (cpf == null || cpf.length != 11) return false

        val clearCpf = cpf.replace(".","").replace("-","")

        val digito1 = calcularDigito(clearCpf.substring(0, 9), pesoCPF)
        val digito2 = calcularDigito(clearCpf.substring(0, 9) + digito1, pesoCPF)
        return clearCpf == clearCpf.substring(0, 9) + digito1.toString() + digito2.toString()
    }

    fun isValidCNPJ(cnpj: String?): Boolean {
        if (cnpj == null || cnpj.length != 14) return false

        val clearCnpj = cnpj.replace(".","").replace("/","").replace("-","")

        val digito1 = calcularDigito(clearCnpj.substring(0, 12), pesoCNPJ)
        val digito2 = calcularDigito(clearCnpj.substring(0, 12) + digito1, pesoCNPJ)
        return clearCnpj == clearCnpj.substring(0, 12) + digito1.toString() + digito2.toString()
    }

    fun formatCPF(cpf: String): String {

        val clearCpf = cpf.replace(".","").replace("-","")

        var exitString = ""

        for(i in clearCpf.indices){
            exitString += when(i) {

                3 -> ".${clearCpf[i]}"

                6 -> ".${clearCpf[i]}"

                9 -> "-${clearCpf[i]}"

                else -> clearCpf[i]
            }
        }

        return exitString
    }

    fun formatCNPJ(cnpj: String): String {

        val clearCnpj = cnpj.replace(".","").replace("/","").replace("-","")

        var exitString = ""

        for(i in clearCnpj.indices){
            exitString += when(i) {

                2 -> ".${clearCnpj[i]}"

                5 -> ".${clearCnpj[i]}"

                8 -> "/${clearCnpj[i]}"

                12 -> "-${clearCnpj[i]}"

                else -> clearCnpj[i]
            }
        }

        return exitString
    }

    fun formatPhone(phone: String): String {

        val clearPhone = phone.replace("(", "")
            .replace(")","")
            .replace(" ","")
            .replace("-","")

        var exitString = ""

        /*(51) 98467-1504*/

        if(clearPhone.length < 11){
            for(i in clearPhone.indices){
                exitString += when(i){

                    0-> "(${clearPhone[i]}"

                    2-> ") ${clearPhone[i]}"

                    6-> "-${clearPhone[i]}"

                    else -> clearPhone[i]
                }
            }
        } else {
            for(i in clearPhone.indices){
                exitString += when(i){

                    0-> "(${clearPhone[i]}"

                    2-> ") ${clearPhone[i]}"

                    7-> "-${clearPhone[i]}"

                    else -> clearPhone[i]
                }
            }
        }

        return exitString
    }
}