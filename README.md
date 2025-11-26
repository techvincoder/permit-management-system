# Permit Management System  

A comprehensive system to manage council permits — designed to handle tenant-based councils, customer applications, staff workflows, and permit approvals/status.  

---

## ⚙️ Project Overview  

- **Purpose**: Provide a unified system for councils to create/manage permits and for customers to submit permit applications online — eliminating manual paperwork and in-person visits.  
- **Intended Users**: Council administrators, staff, and customers seeking permits.  
- **Key Concepts / Entities**:  
  - Council (tenant)  
  - Customer  
  - StaffAccount (roles: staff/admin)  
  - PermitApplication (applicant, permit type, status, timestamps)  

---

## 📦 Modules & Architecture  

- Backend: Java + Spring Boot, Spring Data JPA, PostgreSQL  
- Frontend (MVP): Thymeleaf (for staff UI) — can be extended later with Angular/modern frontend as needed  
- Layers: Controller → Service → Repository/DAO → Database  
- Database: PostgreSQL — multi-tenant aware (tenant = council)  

---

## 🚀 Getting Started  

### Prerequisites  

- Java 11+ (or compatible)  
- Maven  
- PostgreSQL (or another SQL database — adjust config accordingly)  

### Setup  

```bash
git clone https://github.com/techvincoder/permit-management-system.git
cd permit-management-system
# (Optional) adjust application.properties or yaml for DB config
mvn clean install
mvn spring-boot:run
