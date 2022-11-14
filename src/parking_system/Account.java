package parking_system;

import java.util.Objects;

public abstract class Account
{
    private String userName;
    private String password;
    private AccountStatus status;
    private Person person;

    public Account(String userName, String password, AccountStatus status, Person person)
    {
        this.userName = userName;
        this.password = password;
        this.status = status;
        this.person = person;
    }

    public boolean resetPassword(Account other, String newPassword)
    {
        // TODO: Check if shallow equality needs to be checked or deep equality
        if(this == other)
        {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public AccountStatus getStatus()
    {
        return status;
    }

    public void setStatus(AccountStatus status)
    {
        this.status = status;
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return userName.equals(account.userName) && password.equals(account.password) && status == account.status && person.equals(account.person);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userName, password, status, person);
    }

    @Override
    public String toString()
    {
        return "Account{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", person=" + person +
                '}';
    }
}
