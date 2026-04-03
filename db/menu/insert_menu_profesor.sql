-- =================================
-- MENÚ PROFESOR
-- =================================
-- Nivel 1: PROFESOR
INSERT INTO menu (id, category, code, icon, menu_order, name, route, parent_id) VALUES
('2000', 'PROFESOR', 'DASHBOARD', 'dashboard', 1, 'Dashboard', '/profesor/dashboard', NULL),
('2001', 'PROFESOR', 'MIS_CURSOS', 'menu_book', 2, 'Mis Cursos', '/profesor/cursos', NULL),
('2002', 'PROFESOR', 'CALENDARIO', 'calendar_today', 3, 'Calendario / Planificación', '/profesor/calendario', NULL),
('2003', 'PROFESOR', 'ASISTENCIA', 'check_circle', 4, 'Asistencia', '/profesor/asistencia', NULL),
('2004', 'PROFESOR', 'EVALUACIONES', 'grading', 5, 'Evaluaciones / Notas', '/profesor/evaluaciones', NULL),
('2005', 'PROFESOR', 'TAREAS', 'assignment', 6, 'Tareas / Actividades', '/profesor/tareas', NULL),
('2006', 'PROFESOR', 'SEGUIMIENTO', 'insights', 7, 'Seguimiento Académico', '/profesor/seguimiento', NULL),
('2007', 'PROFESOR', 'ESTUDIANTES', 'people', 8, 'Estudiantes', '/profesor/estudiantes', NULL),
('2008', 'PROFESOR', 'DISCIPLINA', 'report', 9, 'Observaciones / Disciplina', '/profesor/disciplina', NULL),
('2009', 'PROFESOR', 'COMUNICACION', 'chat', 10, 'Comunicación', '/profesor/comunicacion', NULL),
('2010', 'PROFESOR', 'RECURSOS', 'folder', 11, 'Recursos / Materiales', '/profesor/recursos', NULL),
('2011', 'PROFESOR', 'COMEDOR', 'restaurant', 12, 'Comedor', '/profesor/comedor', NULL),
('2012', 'PROFESOR', 'CONFIGURACION', 'settings', 13, 'Configuración', '/profesor/configuracion', NULL),
('2013', 'PROFESOR', 'REPORTES', 'bar_chart', 14, 'Reportes', '/profesor/reportes', NULL);

-- ===============================
-- Submenús Dashboard
INSERT INTO menu (id, category, code, icon, menu_order, name, route, parent_id) VALUES
('2100', 'PROFESOR', 'DASH_ALERTAS', 'notification_important', 1, 'Alertas académicas', '/profesor/dashboard/alertas', '2000'),
('2101', 'PROFESOR', 'DASH_RESUMEN', 'assessment', 2, 'Resumen del día', '/profesor/dashboard/resumen', '2000'),
('2102', 'PROFESOR', 'DASH_ACTIVIDAD', 'history', 3, 'Actividad reciente', '/profesor/dashboard/actividad', '2000');

-- ===============================
-- Submenús Mis Cursos
INSERT INTO menu (id, category, code, icon, menu_order, name, route, parent_id) VALUES
('2200', 'PROFESOR', 'CURSOS_LISTA', 'list', 1, 'Lista de cursos', '/profesor/cursos/lista', '2001'),
('2201', 'PROFESOR', 'CURSOS_SECCIONES', 'view_list', 2, 'Secciones', '/profesor/cursos/secciones', '2001'),
('2202', 'PROFESOR', 'CURSO_DETALLE', 'menu_book', 3, 'Curso (detalle)', '/profesor/cursos/detalle', '2001');

-- Submenús Curso Detalle
INSERT INTO menu (id, category, code, icon, menu_order, name, route, parent_id) VALUES
('2210', 'PROFESOR', 'CURSO_RESUMEN', 'summary', 1, 'Resumen', '/profesor/cursos/detalle/resumen', '2202'),
('2211', 'PROFESOR', 'CURSO_ASISTENCIA', 'check_circle', 2, 'Asistencia', '/profesor/cursos/detalle/asistencia', '2202'),
('2212', 'PROFESOR', 'CURSO_NOTAS', 'grading', 3, 'Notas', '/profesor/cursos/detalle/notas', '2202'),
('2213', 'PROFESOR', 'CURSO_TAREAS', 'assignment', 4, 'Tareas', '/profesor/cursos/detalle/tareas', '2202'),
('2214', 'PROFESOR', 'CURSO_RENDIMIENTO', 'insights', 5, 'Rendimiento', '/profesor/cursos/detalle/rendimiento', '2202'),
('2215', 'PROFESOR', 'CURSO_MATERIALES', 'folder', 6, 'Materiales', '/profesor/cursos/detalle/materiales', '2202');

-- ===============================
-- Submenús Calendario / Planificación
INSERT INTO menu (id, category, code, icon, menu_order, name, route, parent_id) VALUES
('2300', 'PROFESOR', 'CALENDARIO_DIARIO', 'calendar_today', 1, 'Diario', '/profesor/calendario/diario', '2002'),
('2301', 'PROFESOR', 'CALENDARIO_SEMANAL', 'date_range', 2, 'Semanal', '/profesor/calendario/semanal', '2002'),
('2302', 'PROFESOR', 'CALENDARIO_MENSUAL', 'event_note', 3, 'Mensual', '/profesor/calendario/mensual', '2002'),
('2303', 'PROFESOR', 'CALENDARIO_CLASES', 'school', 4, 'Clases', '/profesor/calendario/clases', '2002'),
('2304', 'PROFESOR', 'CALENDARIO_EVALUACIONES', 'grading', 5, 'Evaluaciones', '/profesor/calendario/evaluaciones', '2002'),
('2305', 'PROFESOR', 'CALENDARIO_TAREAS', 'assignment', 6, 'Tareas', '/profesor/calendario/tareas', '2002'),
('2306', 'PROFESOR', 'CALENDARIO_ACCIONES', 'flash_on', 7, 'Acciones rápidas', '/profesor/calendario/acciones', '2002');

-- ===============================
-- Acciones rápidas submenú
INSERT INTO menu (id, category, code, icon, menu_order, name, route, parent_id) VALUES
('2310', 'PROFESOR', 'ACCION_CREAR_EVALUACION', 'create', 1, 'Crear evaluación', '/profesor/calendario/acciones/evaluacion', '2306'),
('2311', 'PROFESOR', 'ACCION_CREAR_TAREA', 'create', 2, 'Crear tarea', '/profesor/calendario/acciones/tarea', '2306'),
('2312', 'PROFESOR', 'ACCION_ASISTENCIA', 'check', 3, 'Registrar asistencia', '/profesor/calendario/acciones/asistencia', '2306');

-- ===============================
-- Submenús Comedor
INSERT INTO menu (id, category, code, icon, menu_order, name, route, parent_id) VALUES
('2400', 'PROFESOR', 'COMEDOR_MENU_DIA', 'restaurant_menu', 1, 'Menú del día', '/profesor/comedor/menu-dia', '2011'),
('2401', 'PROFESOR', 'COMEDOR_MENU_SEMANA', 'date_range', 2, 'Menú semanal', '/profesor/comedor/menu-semana', '2011'),
('2402', 'PROFESOR', 'COMEDOR_RESTRICCIONES', 'warning', 3, 'Restricciones alimentarias', '/profesor/comedor/restricciones', '2011'),
('2403', 'PROFESOR', 'COMEDOR_CONSUMO', 'visibility', 4, 'Consumo por estudiante', '/profesor/comedor/consumo', '2011');

-- ===============================
-- Submenús Configuración
INSERT INTO menu (id, category, code, icon, menu_order, name, route, parent_id) VALUES
('2500', 'PROFESOR', 'CONFIG_PERFIL', 'person', 1, 'Perfil docente', '/profesor/configuracion/perfil', '2012'),
('2501', 'PROFESOR', 'CONFIG_NOTIFICACIONES', 'notifications', 2, 'Preferencias de notificación', '/profesor/configuracion/notificaciones', '2012'),
('2502', 'PROFESOR', 'CONFIG_ALERTAS', 'warning', 3, 'Configuración de alertas', '/profesor/configuracion/alertas', '2012'),
('2503', 'PROFESOR', 'CONFIG_EVALUACIONES', 'grading', 4, 'Configuración de evaluaciones', '/profesor/configuracion/evaluaciones', '2012');

-- ===============================
-- Submenús Reportes
INSERT INTO menu (id, category, code, icon, menu_order, name, route, parent_id) VALUES
('2600', 'PROFESOR', 'REPORTE_NOTAS', 'grading', 1, 'Notas', '/profesor/reportes/notas', '2013'),
('2601', 'PROFESOR', 'REPORTE_ASISTENCIA', 'check_circle', 2, 'Asistencia', '/profesor/reportes/asistencia', '2013'),
('2602', 'PROFESOR', 'REPORTE_RENDIMIENTO', 'insights', 3, 'Rendimiento', '/profesor/reportes/rendimiento', '2013'),
('2603', 'PROFESOR', 'REPORTE_TAREAS', 'assignment', 4, 'Tareas / Actividades', '/profesor/reportes/tareas', '2013');