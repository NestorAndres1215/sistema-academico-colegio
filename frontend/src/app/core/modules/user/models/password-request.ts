export interface PasswordRequest {
    currentPassword: string;
    newPassword: string;
    confirmNewPassword: string;
}
export interface UpdatePasswordRequest {
    newPassword: string;
    confirmNewPassword: string;
}
