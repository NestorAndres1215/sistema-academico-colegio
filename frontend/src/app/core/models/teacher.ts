export interface Teacher {
  email: string;
  password: string;

  firstName: string;
  middleName?: string;
  paternalLastName: string;
  maternalLastName: string;

  dni: string;
  phone: string;

  birthDate: string; // "yyyy-MM-dd"
  nationality: string;

  gender: string; // "M" | "F" | "O" (según backend)

  specialization: string;
  hireDate: string; // "yyyy-MM-dd"

  age?: number;     // opcional si backend lo ignora
  profile?: string; // opcional (no es file)
}