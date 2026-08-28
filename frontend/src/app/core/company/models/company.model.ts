export interface CompanyModel {
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
}