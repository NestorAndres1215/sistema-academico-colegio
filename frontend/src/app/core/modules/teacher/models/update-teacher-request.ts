export interface UpdateTeacherRequest {
    firstName: string;
    middleName?: string;
    paternalLastName: string;
    maternalLastName: string;
    dni: string;
    birthDate: string;
    gender: string;
    maritalStatus: string;
    phone?: string;
    address?: string;
    specialty?: string;
    academicDegree?: string;
    professionalLicenseNumber?: string;
}