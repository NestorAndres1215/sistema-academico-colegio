export interface UserHistoryFilter {
  email: string;
  page: number;
  size: number;
  sort: string;
  action?: string | null;
  status?: string | null;
  dateFrom?: Date | null;
  dateTo?: Date | null;
}
