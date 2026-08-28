import { CompanyStatus } from "./company-status.enum";

export interface Company {
  id: number;
  code: string;
  name: string;
  businessName: string;
  taxId: string;

  email: string | null;
  phone: string | null;
  address: string | null;

  country: string;
  city: string | null;

  logoUrl: string | null;
  website: string | null;

  status: CompanyStatus;

  createdAt: Date;
  updatedAt: Date;
}