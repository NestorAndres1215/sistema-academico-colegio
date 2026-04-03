INSERT INTO menu (id, category, code, icon, menu_order, name, route, parent_id) VALUES

-- =========================
-- NIVEL 1 (ROOT)
-- =========================
('2000', 'PORTERO', 'PORTER_ROOT', 'security', '1', 'Portero / Seguridad', NULL, NULL),

-- =========================
-- NIVEL 2
-- =========================
('2001', 'PORTERO', 'PORTER_DASHBOARD', 'dashboard', '1', 'Dashboard', '/portero/dashboard', '2000'),

('2002', 'PORTERO', 'ACCESS_CONTROL', 'qr_code_scanner', '2', 'Control de Acceso', NULL, '2000'),
('2003', 'PORTERO', 'VISITS', 'groups', '3', 'Visitas', NULL, '2000'),
('2004', 'PORTERO', 'STUDENTS_SEARCH', 'school', '4', 'Alumnos', NULL, '2000'),
('2005', 'PORTERO', 'PARENTS_SEARCH', 'people', '5', 'Apoderados', NULL, '2000'),
('2006', 'PORTERO', 'CALENDAR', 'calendar_month', '6', 'Calendario', NULL, '2000'),
('2007', 'PORTERO', 'ATTENDANCE', 'fact_check', '7', 'Asistencia', NULL, '2000'),
('2008', 'PORTERO', 'INCIDENTS', 'report', '8', 'Incidencias', NULL, '2000'),
('2009', 'PORTERO', 'SETTINGS', 'settings', '9', 'Configuración', NULL, '2000'),

-- =========================
-- NIVEL 3 - DASHBOARD
-- =========================
('2123', 'PORTERO', 'TODAY_SUMMARY', 'insights', '1', 'Resumen del día', '/portero/resumen', '2001'),

-- =========================
-- NIVEL 3 - CONTROL DE ACCESO
-- =========================
('2100', 'PORTERO', 'ACCESS_ENTRY', 'login', '1', 'Registrar ingreso', '/portero/acceso/ingreso', '2002'),
('2101', 'PORTERO', 'ACCESS_EXIT', 'logout', '2', 'Registrar salida', '/portero/acceso/salida', '2002'),
('2102', 'PORTERO', 'ACCESS_HISTORY', 'history', '3', 'Historial', '/portero/acceso/historial', '2002'),
('2120', 'PORTERO', 'QR_SCAN', 'qr_code', '4', 'Escanear QR / DNI', '/portero/scan', '2002'),
('2121', 'PORTERO', 'STUDENT_EXIT_CONTROL', 'exit_to_app', '5', 'Control de salida de alumnos', '/portero/salida-alumnos', '2002'),

-- =========================
-- NIVEL 3 - VISITAS
-- =========================
('2103', 'PORTERO', 'VISIT_REGISTER', 'person_add', '1', 'Registrar visita', '/portero/visitas/registro', '2003'),
('2104', 'PORTERO', 'VISIT_LIST', 'list', '2', 'Lista de visitas', '/portero/visitas/lista', '2003'),
('2105', 'PORTERO', 'VISIT_VALIDATE', 'verified_user', '3', 'Validar autorización', '/portero/visitas/validar', '2003'),
('2122', 'PORTERO', 'PHOTO_LOG', 'photo_camera', '4', 'Registro con foto', '/portero/visitas/fotos', '2003'),

-- =========================
-- NIVEL 3 - ALUMNOS
-- =========================
('2106', 'PORTERO', 'STUDENT_SEARCH', 'search', '1', 'Buscar alumno', '/portero/alumnos/buscar', '2004'),

-- =========================
-- NIVEL 3 - APODERADOS
-- =========================
('2107', 'PORTERO', 'PARENT_SEARCH', 'search', '1', 'Buscar apoderado', '/portero/apoderados/buscar', '2005'),

-- =========================
-- NIVEL 3 - CALENDARIO
-- =========================
('2108', 'PORTERO', 'CALENDAR_VIEW', 'calendar_view_month', '1', 'Ver calendario', '/portero/calendario', '2006'),
('2109', 'PORTERO', 'CALENDAR_EVENTS', 'event', '2', 'Eventos', '/portero/calendario/eventos', '2006'),
('2110', 'PORTERO', 'CALENDAR_SCHEDULE', 'schedule', '3', 'Horarios', '/portero/calendario/horarios', '2006'),
('2125', 'PORTERO', 'ROUTE_TRACKING', 'map', '4', 'Ubicación de transporte', '/portero/rutas', '2006'),

-- =========================
-- NIVEL 3 - ASISTENCIA
-- =========================
('2111', 'PORTERO', 'ATTENDANCE_MARK', 'edit_calendar', '1', 'Marcar asistencia', '/portero/asistencia/marcar', '2007'),
('2112', 'PORTERO', 'ATTENDANCE_TODAY', 'today', '2', 'Asistencia del día', '/portero/asistencia/hoy', '2007'),
('2113', 'PORTERO', 'ATTENDANCE_LATE', 'watch_later', '3', 'Tardanzas', '/portero/asistencia/tardanzas', '2007'),

-- =========================
-- NIVEL 3 - INCIDENCIAS
-- =========================
('2114', 'PORTERO', 'INCIDENT_REGISTER', 'post_add', '1', 'Registrar incidencia', '/portero/incidencias/registro', '2008'),
('2115', 'PORTERO', 'INCIDENT_LIST', 'list_alt', '2', 'Ver incidencias', '/portero/incidencias/lista', '2008'),
('2124', 'PORTERO', 'BLOCKED_PERSONS', 'block', '3', 'Personas restringidas', '/portero/restringidos', '2008'),

-- =========================
-- NIVEL 3 - CONFIGURACIÓN
-- =========================
('2116', 'PORTERO', 'CONFIG_ACCESS', 'tune', '1', 'Parámetros de acceso', '/portero/configuracion/acceso', '2009'),
('2117', 'PORTERO', 'CONFIG_VISITS', 'settings_applications', '2', 'Configuración de visitas', '/portero/configuracion/visitas', '2009'),
('2118', 'PORTERO', 'CONFIG_ALERTS', 'notification_important', '3', 'Alertas y notificaciones', '/portero/configuracion/alertas', '2009'),
('2119', 'PORTERO', 'REALTIME_ALERTS', 'notifications_active', '4', 'Alertas en tiempo real', '/portero/alertas', '2009');