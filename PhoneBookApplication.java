
package phonebookapplication;
import java.util.Scanner;

class Contact {
    String name;
    String mobileNumber;
    String telephoneNumber;
    String emailAddress;
    String company;
    String department;
    String position;
    Contact prev;
    Contact next;

    public Contact(String name, String mobileNumber, String telephoneNumber, String emailAddress, String company, String department, String position) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
        this.company = company;
        this.department = department;
        this.position = position;
        this.prev = null;
        this.next = null;
    }

    public void displayContact() {
        System.out.println("Name: " + name);
        System.out.println("Mobile Number: " + mobileNumber);
        System.out.println("Telephone Number: " + telephoneNumber);
        System.out.println("Email Address: " + emailAddress);
        System.out.println("Company: " + company);
        System.out.println("Department: " + department);
        System.out.println("Position: " + position);
        System.out.println("---------------------------------");
    }
}

class PhoneBook {
    private Contact head = null;
    private Contact tail = null;

    public void addContact(String name, String mobileNumber, String telephoneNumber, String emailAddress, String company, String department, String position) {
        Contact newContact = new Contact(name, mobileNumber, telephoneNumber, emailAddress, company, department, position);

        // If list is empty, add as the only contact
        if (head == null) {
            head = newContact;
            tail = newContact;
        } else {
            Contact current = head;

            // Traverse the list to find the correct position for the new contact
            while (current != null && current.name.compareToIgnoreCase(newContact.name) < 0) {
                current = current.next;
            }

            // Insert the contact at the correct position
            if (current == head) {
                // Insert at the beginning
                newContact.next = head;
                head.prev = newContact;
                head = newContact;
            } else if (current == null) {
                // Insert at the end
                tail.next = newContact;
                newContact.prev = tail;
                tail = newContact;
            } else {
                // Insert in the middle
                newContact.prev = current.prev;
                newContact.next = current;
                current.prev.next = newContact;
                current.prev = newContact;
            }
        }

        System.out.println("Contact saved successfully.");
    }

    public void displayAllContacts() {
        if (head == null) {
            System.out.println("No contacts found.");
            return;
        }
        Contact current = head;
        while (current != null) {
            current.displayContact();
            current = current.next;
        }
    }

    public void searchContactByName(String name) {
        if (head == null) {
            System.out.println("No contacts found.");
            return;
        }
        Contact current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                current.displayContact();
                return;
            }
            current = current.next;
        }
        System.out.println("Contact not found.");
    }

    public void updateContact(String name, String newMobileNumber, String newTelephoneNumber, String newEmailAddress, String newCompany, String newDepartment, String newPosition) {
        Contact current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                current.mobileNumber = newMobileNumber;
                current.telephoneNumber = newTelephoneNumber;
                current.emailAddress = newEmailAddress;
                current.company = newCompany;
                current.department = newDepartment;
                current.position = newPosition;
                System.out.println("Contact updated successfully.");
                return;
            }
            current = current.next;
        }
        System.out.println("Contact not found.");
    }

    public void deleteContact(String name) {
        if (head == null) {
            System.out.println("No contacts to delete.");
            return;
        }
        Contact current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                if (current == head) {
                    head = current.next;
                    if (head != null) head.prev = null;
                } else if (current == tail) {
                    tail = current.prev;
                    if (tail != null) tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                System.out.println("Contact deleted successfully.");
                return;
            }
            current = current.next;
        }
        System.out.println("Contact not found.");
    }
}

public class PhoneBookApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();

        // Admin sign-in
        System.out.println("Admin Sign In");
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        if ("admin".equals(username) && "ilovedsa".equals(password)) {
            System.out.println("Sign in successfully.");
        } else {
            System.out.println("Invalid credentials. Exiting...");
            return;
        }

        // Main phone book operations
        while (true) {
            System.out.println("\n---  Welcome to Phone Book Menu ---");
            System.out.println("1. Add Contact");
            System.out.println("2. Display All Contacts");
            System.out.println("3. Search Contact by Name");
            System.out.println("4. Update Contact");
            System.out.println("5. Delete Contact");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter mobile number: ");
                    String mobileNumber = scanner.nextLine();
                    System.out.print("Enter telephone number: ");
                    String telephoneNumber = scanner.nextLine();
                    System.out.print("Enter email address: ");
                    String emailAddress = scanner.nextLine();
                    System.out.print("Enter company: ");
                    String company = scanner.nextLine();
                    System.out.print("Enter department: ");
                    String department = scanner.nextLine();
                    System.out.print("Enter position: ");
                    String position = scanner.nextLine();
                    phoneBook.addContact(name, mobileNumber, telephoneNumber, emailAddress, company, department, position);
                    break;

                case 2:
                    phoneBook.displayAllContacts();
                    break;

                case 3:
                    System.out.print("Enter the name to search: ");
                    String searchName = scanner.nextLine();
                    phoneBook.searchContactByName(searchName);
                    break;

                case 4:
                    System.out.print("Enter the name of the contact to update: ");
                    String updateName = scanner.nextLine();
                    System.out.print("Enter new mobile number: ");
                    String newMobileNumber = scanner.nextLine();
                    System.out.print("Enter new telephone number: ");
                    String newTelephoneNumber = scanner.nextLine();
                    System.out.print("Enter new email address: ");
                    String newEmailAddress = scanner.nextLine();
                    System.out.print("Enter new company: ");
                    String newCompany = scanner.nextLine();
                    System.out.print("Enter new department: ");
                    String newDepartment = scanner.nextLine();
                    System.out.print("Enter new position: ");
                    String newPosition = scanner.nextLine();
                    phoneBook.updateContact(updateName, newMobileNumber, newTelephoneNumber, newEmailAddress, newCompany, newDepartment, newPosition);
                    break;

                case 5:
                    System.out.print("Enter the name of the contact to delete: ");
                    String deleteName = scanner.nextLine();
                    phoneBook.deleteContact(deleteName);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
