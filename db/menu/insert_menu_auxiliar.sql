INSERT INTO menu (id, category, code, icon, menu_order, name, route, parent_id) VALUES

-- =========================
-- NIVEL 1 (ROOT AUXILIAR)
-- =========================
('3000', 'AUXILIAR', 'AUX_ROOT', 'assignment_ind', '1', 'Auxiliar', NULL, NULL),

-- =========================
-- NIVEL 2
-- =========================
('3001', 'AUXILIAR', 'AUX_DASHBOARD', 'dashboard', '1', 'Dashboard', '/auxiliar/dashboard', '3000'),

('3002', 'AUXILIAR', 'AUX_STUDENTS', 'school', '2', 'Alumnos', NULL, '3000'),
('3003', 'AUXILIAR', 'AUX_ATTENDANCE', 'fact_check', '3', 'Asistencia', NULL, '3000'),
('3004', 'AUXILIAR', 'AUX_CALENDAR', 'calendar_month', '4', 'Calendario', NULL, '3000'),
('3005', 'AUXILIAR', 'AUX_INCIDENTS', 'report', '5', 'Incidencias', NULL, '3000'),
('3006', 'AUXILIAR', 'AUX_PARENTS', 'people', '6', 'Apoderados', NULL, '3000'),
('3007', 'AUXILIAR', 'AUX_REPORTS', 'assessment', '7', 'Reportes', NULL, '3000'),
('3008', 'AUXILIAR', 'AUX_SETTINGS', 'settings', '8', 'Configuración', NULL, '3000'),

-- =========================
-- NIVEL 3 - DASHBOARD
-- =========================
('3100', 'AUXILIAR', 'AUX_SUMMARY', 'insights', '1', 'Resumen del día', '/auxiliar/resumen', '3001'),

-- =========================
-- NIVEL 3 - ALUMNOS
-- =========================
('3101', 'AUXILIAR', 'STUDENT_LIST', 'list', '1', 'Lista de alumnos', '/auxiliar/alumnos/lista', '3002'),
('3102', 'AUXILIAR', 'STUDENT_SEARCH', 'search', '2', 'Buscar alumno', '/auxiliar/alumnos/buscar', '3002'),
('3103', 'AUXILIAR', 'STUDENT_PROFILE', 'person', '3', 'Perfil / Observaciones', '/auxiliar/alumnos/perfil', '3002'),

-- =========================
-- NIVEL 3 - ASISTENCIA
-- =========================
('3104', 'AUXILIAR', 'ATTENDANCE_REGISTER', 'edit_calendar', '1', 'Registrar asistencia', '/auxiliar/asistencia/registrar', '3003'),
('3105', 'AUXILIAR', 'ATTENDANCE_TODAY', 'today', '2', 'Asistencia del día', '/auxiliar/asistencia/hoy', '3003'),
('3106', 'AUXILIAR', 'LATE_STUDENTS', 'watch_later', '3', 'Tardanzas', '/auxiliar/asistencia/tardanzas', '3003'),
('3107', 'AUXILIAR', 'ABSENCES', 'event_busy', '4', 'Inasistencias', '/auxiliar/asistencia/inasistencias', '3003'),

-- =========================
-- NIVEL 3 - CALENDARIO
-- =========================
('3108', 'AUXILIAR', 'CALENDAR_VIEW', 'calendar_view_month', '1', 'Ver calendario', '/auxiliar/calendario', '3004'),
('3109', 'AUXILIAR', 'CALENDAR_EVENTS', 'event', '2', 'Eventos', '/auxiliar/calendario/eventos', '3004'),
('3110', 'AUXILIAR', 'CALENDAR_SCHEDULE', 'schedule', '3', 'Horarios', '/auxiliar/calendario/horarios', '3004'),

-- =========================
-- NIVEL 3 - INCIDENCIAS
-- =========================
('3111', 'AUXILIAR', 'INCIDENT_REGISTER', 'post_add', '1', 'Registrar incidencia', '/auxiliar/incidencias/registro', '3005'),
('3112', 'AUXILIAR', 'INCIDENT_LIST', 'list_alt', '2', 'Ver incidencias', '/auxiliar/incidencias/lista', '3005'),
('3113', 'AUXILIAR', 'INCIDENT_TRACKING', 'track_changes', '3', 'Seguimiento', '/auxiliar/incidencias/seguimiento', '3005'),

-- =========================
-- NIVEL 3 - APODERADOS
-- =========================
('3114', 'AUXILIAR', 'PARENT_VIEW', 'visibility', '1', 'Ver apoderados', '/auxiliar/apoderados', '3006'),

-- =========================
-- NIVEL 3 - REPORTES
-- =========================
('3115', 'AUXILIAR', 'REPORT_ATTENDANCE', 'bar_chart', '1', 'Reporte de asistencia', '/auxiliar/reportes/asistencia', '3007'),
('3116', 'AUXILIAR', 'REPORT_INCIDENTS', 'assessment', '2', 'Reporte de incidencias', '/auxiliar/reportes/incidencias', '3007'),

-- =========================
-- NIVEL 3 - CONFIGURACIÓN
-- =========================
('3117', 'AUXILIAR', 'SETTINGS_NOTIFICATIONS', 'notifications', '1', 'Notificaciones', '/auxiliar/configuracion/notificaciones', '3008'),
('3118', 'AUXILIAR', 'SETTINGS_PREFERENCES', 'tune', '2', 'Preferencias', '/auxiliar/configuracion/preferencias', '3008'),
('3119', 'AUXILIAR', 'SETTINGS_PROFILE', 'person', '3', 'Perfil de usuario', '/auxiliar/configuracion/perfil', '3008');