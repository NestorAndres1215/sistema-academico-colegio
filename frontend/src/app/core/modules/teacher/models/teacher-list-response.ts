export interface TeacherListResponse {

  id: number;
  code: string;

  // User
  email: string;
  username: string;

  // Nombre completo
  name: string;
  lastName: string;

  // Datos personales
  firstName: string;
  middleName: string;
  paternalLastName: string;
  maternalLastName: string;

  dni: string;
  birthDate: string;
  gender: string;
  maritalStatus: string;

  // Contacto
  phone: string;
  cv:string;
  address: string;

  // Información profesional
  specialty: string;
  academicDegree: string;
  professionalLicenseNumber: string;

  // Información académica
  university: string;
  graduationDate: string;
  yearsOfExperience: number;

  // Información adicional
  notes: string;
  photo: string;
  status: string;
}