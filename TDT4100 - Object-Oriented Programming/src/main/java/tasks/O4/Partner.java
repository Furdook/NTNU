package tasks.O4;

public class Partner {
    private String name;
    private Partner partner;

    public Partner(String name) {
        this.name = name;
    }

    public void setPartner(Partner partner) {
        if (partner == null) {
            this.partner.partner = null;
            this.partner = null;
            return;
        }
        // Checking if partner already has a different partner:
        if (partner.partner instanceof Partner) {
            this.partner.partner = null;
            partner.partner.partner = null;
        }
        this.partner = partner;
        partner.partner = this;
    }

    public String getName() {
        return this.name;
    }

    public Partner getPartner() {
        return this.partner;
    }
}
