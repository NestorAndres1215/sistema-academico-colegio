export interface TeacherRequest {
    firstName: string;
    middleName: string;
    paternalLastName: string;
    maternalLastName: string;
    email: string;
    dni: string;
    birthDate: string;
    gender: string;
    maritalStatus: string;
    phone: string;
    address: string;
    specialty: string;
    academicDegree: string;
    professionalLicenseNumber: string;

    university: string;
    graduationDate: string;
    yearsOfExperience: number;
    notes: string;

    contractType: string;
    startDate: string;
    endDate: string;
    position: string;
    weeklyHours: number;
    salary: number;
}