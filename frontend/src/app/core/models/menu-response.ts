import { RoleResponse } from "./role-response";



export interface MenuResponse {
    id: string;
    code: string;
    name: string;
    icon?: string;
    route?: string;
    menuOrder?: string;
    category?: string;
    parent?: MenuResponse | null;
    roles: RoleResponse[];
    children?: MenuResponse[];
    mostrarSubMenu?: boolean;
}