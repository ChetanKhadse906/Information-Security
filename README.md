# 🔐 Information Security Practicals

This repository contains the practical implementations for the **Information Security** subject of **MCA (AI & ML), Semester III**, at **Shri Ramdeobaba University (RBU), Nagpur**.

All practical programs are implemented in **Java** to develop a hands-on understanding of cryptography, encryption and decryption, key exchange, cryptographic hashing, digital signatures, and fundamental information security principles.

## 📚 List of Practicals

| Sr. No. | Practical Aim |
|---|---|
| 1 | Implement Additive Cipher, Multiplicative Cipher, and Affine Cipher for encryption and decryption. |
| 2 | Implement the Hill Cipher (2×2 and 3×3) with key matrix validation and encryption/decryption. |
| 3 | Implement the Playfair Cipher using a user-defined key and a generated 5×5 matrix. |
| 4 | Implement a Transposition Cipher using Rail Fence or Columnar Transposition techniques. |
| 5 | Implement DES encryption and decryption and demonstrate the Avalanche Effect. |
| 6 | Implement the Extended Euclidean Algorithm and RSA Public Key Cryptosystem. |
| 7 | Implement the Diffie–Hellman Key Exchange Algorithm to establish a shared secret key. |
| 8 | Implement the MD5 Cryptographic Hash Function and compare hashes for modified plaintext. |
| 9 | Implement the SHA-256 Cryptographic Hash Function and observe changes in hash values. |
| 10 | Generate and verify Digital Signatures using the RSA Digital Signature Scheme. |

## 🎯 Objectives

- Understand the fundamentals of information security and cryptography.
- Learn how plaintext is converted into ciphertext through encryption and recovered through decryption.
- Implement classical substitution and transposition ciphers.
- Understand symmetric-key and asymmetric-key cryptography.
- Generate public and private keys and understand their roles.
- Implement key exchange, hashing, and digital signature techniques.
- Explore the importance of confidentiality, integrity, and availability.
- Understand the role of cryptography in protecting digital communication and sensitive information.

## 💡 What I Learned Through These Practicals

By implementing these practicals in **Java**, I gained a practical understanding of how cryptography helps protect data and secure communication in the digital world.

### 1. Plaintext to Ciphertext

I learned how readable information, called **plaintext**, is transformed into unreadable **ciphertext** through encryption using an algorithm and the appropriate key. Decryption converts ciphertext back into readable information when the correct key and method are used.

### 2. Importance of Cryptography

Cryptography plays an important role in protecting sensitive information during storage and transmission. It is used in online banking, digital payments, e-commerce, secure websites, password protection systems, and private communication.

### 3. Different Cryptographic Algorithms

Through these practicals, I explored different algorithms and techniques, including:

- **Classical Ciphers:** Additive, Multiplicative, Affine, Hill, Playfair, and Transposition ciphers.
- **Symmetric-Key Cryptography:** DES encryption and decryption.
- **Asymmetric-Key Cryptography:** RSA public-key cryptosystem.
- **Key Exchange:** Diffie–Hellman key exchange.
- **Cryptographic Hashing:** MD5 and SHA-256.
- **Digital Signatures:** RSA digital signature generation and verification.

Each technique has a different purpose and demonstrates a different aspect of cryptography and information security.

### 4. Public-Key and Private-Key Cryptography

I learned the difference between symmetric and asymmetric cryptography.

- **Symmetric-Key Cryptography:** Uses the same shared secret key for encryption and decryption.
- **Asymmetric-Key Cryptography:** Uses a mathematically related public and private key pair. Depending on the operation, the keys support encryption and decryption or digital signature generation and verification.

I also learned how RSA uses public and private keys and how Diffie–Hellman allows two parties to establish a shared secret over a communication channel.

### 5. Digital Signatures

I learned how digital signatures help verify the authenticity of a signer and the integrity of a message. They can also support non-repudiation, subject to the security of the keys and the surrounding system.

A digital signature is different from encryption: its primary purpose is to support authenticity and integrity, not to hide the contents of a message.

### 6. Authentication, Authorization, and Man-in-the-Middle Attacks

I explored the importance of distinguishing between:

- **Authentication:** Verifying the identity of a user or system.
- **Authorization:** Determining what an authenticated user or system is permitted to access.
- **Man-in-the-Middle (MITM) Attacks:** Attacks in which an attacker intercepts or potentially modifies communication between two parties.

Authenticated key exchange, digital certificates, and secure communication protocols such as TLS help protect against MITM attacks. Encryption without proper authentication may not be sufficient to prevent them.

### 7. Hashing and Data Integrity

I learned how cryptographic hash functions transform input data into fixed-length hash values, also called digests.

By implementing MD5 and SHA-256, I explored how modifying the input changes its hash value. Hashing is useful for integrity checks, although verifying authenticity requires additional mechanisms such as a message authentication code or a digital signature.

## 🛡️ Fundamental Principles of Information Security — CIA Triad

The **CIA Triad** represents three fundamental principles of information security.

| Principle | Meaning | Example |
|---|---|---|
| **Confidentiality** | Ensures that information is accessible only to authorized individuals or systems. | Encrypting sensitive data. |
| **Integrity** | Ensures that information remains accurate and has not been altered without authorization. | Verifying data using cryptographic hashes or digital signatures. |
| **Availability** | Ensures that information and services remain accessible to authorized users when required. | Protecting systems against outages and denial-of-service attacks. |

These principles work together to help protect information and maintain the security and reliability of digital systems.

## 🌐 Security Is Everywhere

Information security is essential in everyday digital activities, including online banking, e-commerce, email communication, digital payments, cloud storage, and secure websites.

Through these practical implementations, I developed a better understanding of how cryptographic algorithms contribute to data protection, secure communication, authentication, and integrity verification.

I also learned that **no single algorithm provides complete security**. Secure systems require appropriate cryptographic techniques, proper key management, authentication, authorization, and secure protocols.

## 🛠️ Technologies Used

- **Programming Language:** Java
- **Version Control:** Git
- **Repository Hosting:** GitHub
- **Domain:** Information Security and Cryptography

## ▶️ How to Run the Programs

### Prerequisites

- Java Development Kit (JDK) installed.
- A code editor or IDE, such as Visual Studio Code, IntelliJ IDEA, or Eclipse.
- Git installed to clone the repository.

### Step 1: Clone the Repository

```bash
git clone https://github.com/ChetanKhadse906/Information-Security.git
```

### Step 2: Navigate to the Project Directory

```bash
cd Information-Security
```

### Step 3: Compile a Java Program

Navigate to the directory containing the required Java source file and compile it.

```bash
javac Main.java
```

### Step 4: Execute the Program

```bash
java Main
```

Replace `Main.java` and `Main` with the actual filename and class name of the practical you want to execute. Follow the input prompts provided by each program.

## 📖 Learning Outcomes

After completing these practicals, I developed an understanding of:

- Converting plaintext into ciphertext and performing decryption.
- Implementing classical ciphers using Java.
- Understanding symmetric and asymmetric cryptographic techniques.
- Generating RSA public and private keys.
- Establishing a shared secret using Diffie–Hellman key exchange.
- Generating and comparing MD5 and SHA-256 hash values.
- Understanding the Avalanche Effect in DES.
- Generating and verifying RSA digital signatures.
- Understanding confidentiality, integrity, and availability.
- Recognizing the roles of authentication, authorization, and secure key exchange in information security.
- Understanding how cryptographic techniques help protect digital data and communication.

## ⚠️ Security Note

These programs are implemented for **academic learning and educational purposes**.

DES and MD5 are considered insecure for modern security applications. These implementations demonstrate cryptographic concepts and should not be used as production-grade security solutions. Modern systems should use appropriately configured, well-reviewed cryptographic algorithms and libraries.

## 👨‍🎓 Academic Information

- **Subject:** Information Security
- **Program:** Master of Computer Applications (MCA)
- **Specialization:** Artificial Intelligence and Machine Learning
- **Semester:** III
- **Institution:** Shri Ramdeobaba University (RBU), Nagpur

## 🔗 Repository

[**View Information-Security on GitHub**](https://github.com/ChetanKhadse906/Information-Security)

---

*Learning cryptography through practical implementation and understanding the importance of security in the digital world.*
