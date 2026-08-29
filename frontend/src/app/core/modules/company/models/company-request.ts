export interface CompanyRequest {
  code: string;
  name: string;
  businessName: string;
  taxId: string;
  email?: string;
  phone?: string;
  address?: string;
  country: string;
  city?: string;
  website?: string;
}
