export interface TeacherContractFilter {
  teacherCode?: string;
  startDate?: Date | null;
  endDate?: Date | null;
  page?: number;
  size?: number;
  sort?: string;
}
