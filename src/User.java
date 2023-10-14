public class User {
        int acc_no;
        String name;
        String email;
        long balance;
        long securitypin;

        public User(){

        }

        public User(int acc_no, String name, String email, long balance, long securitypin){
            this.acc_no = acc_no;
            this.name = name;
            this.email = email;
            this.balance = balance;
            this.securitypin = securitypin;
        }

    public int getAcc_no() {
        return acc_no;
    }

    public void setAcc_no(int acc_no) {
        this.acc_no = acc_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getSecuritypin() {
        return securitypin;
    }

    public void setSecuritypin(long securitypin) {
        this.securitypin = securitypin;
    }

    @Override
    public String toString() {
        return "User{" +
                "acc_no=" + acc_no +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", securitypin=" + securitypin +
                '}';
    }
}
