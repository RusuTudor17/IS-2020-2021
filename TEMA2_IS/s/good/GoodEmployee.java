/*am gandit problema responsabilitatii astfel: un angajat, fiind reprezentat de clasa GoodEmployee, are ca responsabilitate
doar sa raporteze orele lucrate, si eventuale alte detalii ulterioare legat de acest lucru. Am separat functiile de calculare
de salariu, si de salvare in baza de date, in 2 alte clase reprezentand sistemele cu care lucreaza firma unde este angajat
employeerul, clase in care ulterior se pot face usor modificari si imbunatatiri la evidenta asupra angajatului, si modului
cum ii este calculat salariul.
 */
package com.solid.s.good;

public class GoodEmployee {

    private String status;
    private String name;
    private int hours;

    public GoodEmployee(String status, String name, int hours) {
        this.status = status;
        this.name = name;
        this.hours = hours;
    }

    public String reportHours() {
        return String.format("%s worked %d hours.\n", this.name, this.hours);
    }

    @Override
    public String toString() {
        return "I am a employee";
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }
}
