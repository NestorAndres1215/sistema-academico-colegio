export interface PageResponse<T> {
  [x: string]: any;
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}