INSERT INTO menu (id, category, code, icon, menu_order, name, route, parent_id) VALUES

-- =========================
-- NIVEL 1
-- =========================
('1000', 'GENERAL', 'DASHBOARD_ADMIN', 'dashboard', '1', 'Dashboard', '/admin', NULL),
('1001', 'GESTION_GENERAL', 'GESTION_GENERAL', 'settings', '2', 'Gestión General', NULL, NULL),
('1002', 'ACADEMICO', 'ACADEMICO', 'school', '3', 'Académico', NULL, NULL),
('1003', 'ESCOLAR', 'SCHOOL_LIFE', 'event', '4', 'Actividades y Vida Escolar', NULL, NULL),
('1004', 'PROCESOS', 'PROCESSES', 'account_tree', '5', 'Trámites y Procesos', NULL, NULL),
('1005', 'FINANZAS', 'FINANCE', 'payments', '6', 'Finanzas', NULL, NULL),
('1006', 'SEGURIDAD', 'SECURITY', 'security', '7', 'Seguridad', NULL, NULL),
('1007', 'COMUNICACION', 'COMMUNICATION', 'campaign', '8', 'Comunicación', NULL, NULL),
('1008', 'ANALITICA', 'ANALYTICS', 'analytics', '9', 'Analítica', NULL, NULL),
('1009', 'REPORTES', 'REPORTS', 'assessment', '10', 'Reportes', NULL, NULL),
('1010', 'CONFIGURACION', 'SETTINGS', 'settings', '11', 'Configuración', NULL, NULL),
('1011', 'EXTRA', 'EXTRA_MODULES', 'extension', '12', 'Módulos Extra', NULL, NULL),
-- =========================
-- NIVEL 2 - GESTION GENERAL
-- =========================
('1101', 'GESTION_GENERAL', 'USERS', 'group', '1', 'Usuarios', NULL, '1001'),
('1102', 'GESTION_GENERAL', 'PERSONAL', 'badge', '2', 'Personal del Colegio', NULL, '1001'),
('1103', 'GESTION_GENERAL', 'STUDENTS', 'school', '3', 'Estudiantes', NULL, '1001'),
('1104', 'GESTION_GENERAL', 'PARENTS', 'people', '4', 'Apoderados', NULL, '1001'),
('1105', 'GESTION_GENERAL', 'RELACION', 'link', '5', 'Relación estudiante–apoderado', '/relacion-estudiante-apoderado', '1001'),
('1106', 'GESTION_GENERAL', 'DOCUMENTS', 'description', '6', 'Documentos', NULL, '1001'),

-- =========================
-- NIVEL 2 - ACADEMICO
-- =========================

('1107', 'ACADEMICO', 'COURSE_STRUCTURE', 'account_tree', '1', 'Cursos y estructura', NULL, '1002'),
('1108', 'ACADEMICO', 'ENROLLMENT', 'how_to_reg', '2', 'Matrícula', NULL, '1002'),
('1109', 'ACADEMICO', 'ATTENDANCE', 'fact_check', '3', 'Asistencia', NULL, '1002'),
('1110', 'ACADEMICO', 'GRADES', 'grading', '4', 'Notas', NULL, '1002'),
('1111', 'ACADEMICO', 'ACADEMIC_TRACKING', 'insights', '5', 'Seguimiento académico', NULL, '1002'),
('1112', 'ACADEMICO', 'TUTORING', 'psychology', '6', 'Tutoría', NULL, '1002'),
('1113', 'ACADEMICO', 'TEACHING_HOURS', 'schedule', '7', 'Horas docentes', NULL, '1002'),

-- =========================
-- NIVEL 2 - ACTIVIDADES Y VIDA ESCOLAR
-- =========================

('1114', 'ESCOLAR', 'AGENDA', 'calendar_month', '1', 'Agenda', NULL, '1003'),
('1115', 'ESCOLAR', 'EVENTS', 'event_available', '2', 'Eventos del colegio', NULL, '1003'),
('1116', 'ESCOLAR', 'WORKSHOPS', 'groups', '3', 'Talleres', NULL, '1003'),
('1117', 'ESCOLAR', 'DISCIPLINE', 'gavel', '4', 'Disciplina', NULL, '1003'),

-- =========================
-- NIVEL 2 - Tramites
-- =========================

('1118', 'PROCESOS', 'ACADEMIC_PROCESSES', 'description', '1', 'Trámites académicos', NULL, '1004'),
('1119', 'PROCESOS', 'APPROVALS', 'fact_check', '2', 'Aprobaciones', NULL, '1004'),
('1120', 'PROCESOS', 'WORKFLOWS', 'alt_route', '3', 'Flujos automáticos', NULL, '1004'),

-- =========================
-- NIVEL 2 - Finanzas
-- =========================

('1121', 'FINANZAS', 'TUITION', 'request_quote', '1', 'Pensiones', '/finanzas/pensiones', '1005'),
('1122', 'FINANZAS', 'PAYMENTS', 'payments', '2', 'Pagos', '/finanzas/pagos', '1005'),
('1123', 'FINANZAS', 'DEBTS', 'money_off', '3', 'Deudas', '/finanzas/deudas', '1005'),
('1124', 'FINANZAS', 'CASHBOX', 'point_of_sale', '4', 'Caja (ingresos diarios)', '/finanzas/cajas', '1005'),
('1125', 'FINANZAS', 'INVOICES', 'receipt_long', '5', 'Comprobantes', '/finanzas/comprobantes', '1005'),
('1126', 'FINANZAS', 'PAYROLL', 'badge', '6', 'Planilla', NULL, '1005'),
('1127', 'FINANZAS', 'SCHOLARSHIPS', 'card_giftcard', '7', 'Descuentos / Becas', '/finanzas/descuencuestos-becas', '1005'),

-- =========================
-- NIVEL 2 - SEGURIDAD
-- =========================

('1128', 'SEGURIDAD', 'ACCESS_CONTROL', 'qr_code_scanner', '1', 'Control de acceso (QR / DNI)', '/seguridad/acceso', '1006'),
('1129', 'SEGURIDAD', 'DIGITAL_CARD', 'badge', '2', 'Carnet digital', NULL, '1006'),
('1130', 'SEGURIDAD', 'ENTRY_LOG', 'login', '3', 'Registro de entradas/salidas', '/seguridad/entradas', '1006'),
('1131', 'SEGURIDAD', 'AUTHORIZED_CONTACTS', 'contacts', '4', 'Contactos autorizados', '/seguridad/contactos', '1006'),
('1132', 'SEGURIDAD', 'VISITORS', 'person', '5', 'Visitantes', '/seguridad/visitantes', '1006'),
('1133', 'SEGURIDAD', 'ACCESS_LOGS', 'list_alt', '6', 'Logs de acceso', '/seguridad/logs', '1006'),
('1134', 'SEGURIDAD', 'SESSION_CONTROL', 'admin_panel_settings', '7', 'Control de sesiones', '/seguridad/sesiones', '1006'),

-- =========================
-- NIVEL 2 - COMUNICACION
-- =========================
('1135', 'COMUNICACION', 'NOTIFICATION_CENTER', 'notifications', '1', 'Centro de notificaciones', NULL, '1007'),
('1136', 'COMUNICACION', 'ANNOUNCEMENTS', 'campaign', '2', 'Anuncios', '/comunicacion/anuncios', '1007'),
('1137', 'COMUNICACION', 'NOTIFICATIONS', 'notifications_active', '3', 'Notificaciones', '/comunicacion/notificaciones', '1007'),
('1138', 'COMUNICACION', 'INTERNAL_MESSAGES', 'mail', '4', 'Mensajes internos', '/comunicacion/mensajes', '1007'),
('1139', 'COMUNICACION', 'PARENT_COMMUNICATION', 'groups', '5', 'Comunicados a padres', '/comunicacion/padres', '1007'),
('1140', 'COMUNICACION', 'CIRCULARS', 'description', '6', 'Circulares', '/comunicacion/circulares', '1007'),

-- =========================
-- NIVEL 2 - ANALITICA
-- =========================

('1141', 'ANALITICA', 'ANALYTIC_DASHBOARD', 'dashboard', '1', 'Dashboard analítico', NULL, '1008'),
('1142', 'ANALITICA', 'ACADEMIC_CHARTS', 'bar_chart', '2', 'Gráficos académicos', '/analitica/academico', '1008'),
('1143', 'ANALITICA', 'ATTENDANCE_CHARTS', 'fact_check', '3', 'Gráficos de asistencia', '/analitica/asistencia', '1008'),
('1144', 'ANALITICA', 'FINANCIAL_CHARTS', 'payments', '4', 'Gráficos financieros', '/analitica/finanzas', '1008'),
('1145', 'ANALITICA', 'BEHAVIOR_CHARTS', 'psychology', '5', 'Gráficos de comportamiento', '/analitica/comportamiento', '1008'),
('1146', 'ANALITICA', 'COMPARATIVES', 'compare', '6', 'Comparativas', '/analitica/comparativas', '1008'),
('1147', 'ANALITICA', 'ADVANCED_INDICATORS', 'insights', '7', 'Indicadores avanzados', '/analitica/indicadores', '1008'),
-- =========================
-- NIVEL 2 - REPORTES
-- =========================

('1148', 'REPORTES', 'STUDENT_REPORT', 'school', '1', 'Reporte de estudiantes', '/reportes/estudiantes', '1009'),
('1149', 'REPORTES', 'ACADEMIC_REPORT', 'grading', '2', 'Reporte académico', '/reportes/academico', '1009'),
('1150', 'REPORTES', 'ATTENDANCE_REPORT', 'fact_check', '3', 'Reporte de asistencia', '/reportes/asistencia', '1009'),
('1151', 'REPORTES', 'DISCIPLINE_REPORT', 'gavel', '4', 'Reporte de disciplina', '/reportes/disciplina', '1009'),
('1152', 'REPORTES', 'FINANCIAL_REPORT', 'payments', '5', 'Reporte financiero', '/reportes/financiero', '1009'),
('1153', 'REPORTES', 'EXPORT', 'download', '6', 'Exportar (PDF / Excel)', '/reportes/exportar', '1009'),


('1154', 'CONFIGURACION', 'ACADEMIC_YEAR', 'calendar_today', '1', 'Año académico', '/configuracion/anio', '1010'),
('1155', 'CONFIGURACION', 'PERIODS', 'date_range', '2', 'Periodos', '/configuracion/periodos', '1010'),
('1156', 'CONFIGURACION', 'SHIFTS', 'schedule', '3', 'Turnos', '/configuracion/turnos', '1010'),
('1157', 'CONFIGURACION', 'SYSTEM_PARAMS', 'tune', '4', 'Parámetros del sistema', '/configuracion/parametros', '1010'),
('1158', 'CONFIGURACION', 'GRADE_SETTINGS', 'grading', '5', 'Configuración de notas', '/configuracion/notas', '1010'),
('1159', 'CONFIGURACION', 'ATTENDANCE_SETTINGS', 'fact_check', '6', 'Configuración de asistencia', '/configuracion/asistencia', '1010'),
('1160', 'CONFIGURACION', 'DOCUMENT_TYPES', 'description', '7', 'Tipos de documento', '/configuracion/documentos', '1010'),
('1161', 'CONFIGURACION', 'TEMPLATES', 'article', '8', 'Plantillas', NULL, '1010'),
('1162', 'CONFIGURACION', 'BACKUPS', 'backup', '9', 'Copias de seguridad', '/configuracion/backups', '1010'),
('1163', 'CONFIGURACION', 'COMPANY', 'business', '10', 'Datos de la empresa', '/configuracion/empresa', '1010'),


('1164', 'EXTRA', 'LIBRARY', 'menu_book', '1', 'Biblioteca', NULL, '1011'),
('1165', 'EXTRA', 'TRANSPORT', 'directions_bus', '2', 'Transporte escolar', NULL, '1011'),
('1166', 'EXTRA', 'DINING', 'restaurant', '3', 'Comedor', NULL, '1011'),
('1167', 'EXTRA', 'NURSING', 'local_hospital', '4', 'Enfermería', NULL, '1011'),
('1168', 'EXTRA', 'VIRTUAL_PLATFORM', 'computer', '5', 'Plataforma virtual', NULL, '1011'),
-- =========================
-- NIVEL 3 - USUARIOS
-- =========================
('1201', 'GESTION_GENERAL', 'USER_LIST', 'list', '1', 'Lista de usuarios', '/usuarios/listado-usuario', '1101'),
('1202', 'GESTION_GENERAL', 'USER_CREATE', 'person_add', '2', 'Registro usuario', '/usuarios/registro-usuario', '1101'),
('1203', 'GESTION_GENERAL', 'USER_ROLE_ASSIGN', 'admin_panel_settings', '3', 'Asignación de rol', '/usuarios/asignacion-rol', '1101'),
('1204', 'GESTION_GENERAL', 'USER_STATUS', 'toggle_on', '4', 'Estado de cuentas', '/usuarios/estado-cuenta', '1101'),
('1205', 'GESTION_GENERAL', 'USER_RESET_PASSWORD', 'lock_reset', '5', 'Restablecer contraseñas', '/usuarios/restablecer-contrasena', '1101'),

-- =========================
-- NIVEL 3 - PERSONAL
-- =========================
('1206', 'GESTION_GENERAL', 'TEACHERS', 'school', '1', 'Profesores', NULL, '1102'),
('1207', 'GESTION_GENERAL', 'STAFF_LIST', 'badge', '2', 'Personal', NULL, '1102'),
('1208', 'GESTION_GENERAL', 'STAFF_ATTENDANCE', 'event_available', '3', 'Asistencia del personal', '/personal/asistencia', '1102'),
('1209', 'GESTION_GENERAL', 'STAFF_CONTRACTS', 'assignment', '4', 'Gestión de contratos', '/personal/contratos', '1102'),

-- =========================
-- NIVEL 4 - PROFESORES
-- =========================
('1401', 'GESTION_GENERAL', 'TEACHER_PROFILE', 'person', '1', 'Datos personales', '/profesores/datos', '1206'),
('1402', 'GESTION_GENERAL', 'TEACHER_COURSES', 'menu_book', '2', 'Cursos asignados', '/profesores/cursos', '1206'),
('1403', 'GESTION_GENERAL', 'TEACHER_SCHEDULE', 'schedule', '3', 'Horarios', '/profesores/horarios', '1206'),

-- =========================
-- NIVEL 4 - PERSONAL
-- =========================
('1404', 'GESTION_GENERAL', 'DIRECTOR', 'manage_accounts', '1', 'Director', '/personal/director', '1207'),
('1405', 'GESTION_GENERAL', 'SUBDIRECTOR', 'supervisor_account', '2', 'Subdirector', '/personal/subdirector', '1207'),
('1406', 'GESTION_GENERAL', 'SECRETARY', 'support_agent', '3', 'Secretaria', '/personal/secretaria', '1207'),
('1407', 'GESTION_GENERAL', 'ASSISTANT', 'assignment_ind', '4', 'Auxiliar', '/personal/auxiliar', '1207'),
('1408', 'GESTION_GENERAL', 'SECURITY', 'security', '5', 'Portero / Seguridad', '/personal/seguridad', '1207'),
('1409', 'GESTION_GENERAL', 'OTHER_STAFF', 'restaurant', '6', 'Personal de comedor', '/personal/comedor', '1207'),
('1410', 'GESTION_GENERAL', 'TRANSPORTE', 'directions_bus', '7', 'Personal de transporte', '/personal/transporte', '1207'),
('1411', 'GESTION_GENERAL', 'ENFERMERIA', 'local_hospital', '8', 'Enfermería', '/personal/enfermeria', '1207'),
('1412', 'GESTION_GENERAL', 'PSICOLOGIA', 'psychology', '9', 'Psicología', '/personal/psicologia', '1207'),
('1413', 'GESTION_GENERAL', 'BIBLIOTECA', 'menu_book', '10', 'Bibliotecario', '/personal/biblioteca', '1207'),
-- =========================
-- NIVEL 3 - ESTUDIANTES
-- =========================
('1210', 'GESTION_GENERAL', 'STUDENT_LIST', 'list', '1', 'Lista de estudiantes', '/estudiantes/listado', '1103'),
('1211', 'GESTION_GENERAL', 'STUDENT_PROFILE', 'person', '2', 'Datos personales', '/estudiantes/datos', '1103'),
('1212', 'GESTION_GENERAL', 'STUDENT_FILE', 'folder', '3', 'Ficha del estudiante', '/estudiantes/ficha', '1103'),
('1213', 'GESTION_GENERAL', 'STUDENT_STATUS', 'toggle_on', '4', 'Estado (activo/retirado)', '/estudiantes/estado', '1103'),

-- =========================
-- NIVEL 3 - APODERADOS
-- =========================
('1214', 'GESTION_GENERAL', 'PARENT_LIST', 'list', '1', 'Lista de apoderados', '/apoderados/listado', '1104'),
('1215', 'GESTION_GENERAL', 'PARENT_PROFILE', 'person', '2', 'Datos personales', '/apoderados/datos', '1104'),
('1216', 'GESTION_GENERAL', 'PARENT_CONTACT', 'contact_phone', '3', 'Contacto', '/apoderados/contacto', '1104'),

-- =========================
-- NIVEL 3 - DOCUMENTOS
-- =========================
('1217', 'GESTION_GENERAL', 'DOC_STUDENT', 'folder_shared', '1', 'Documentos por estudiante', '/documentos/estudiante', '1106'),
('1218', 'GESTION_GENERAL', 'DOC_UPLOAD', 'upload_file', '2', 'Subida de archivos', '/documentos/subida', '1106'),
('1219', 'GESTION_GENERAL', 'DOC_CONTRACTS', 'description', '3', 'Contratos', '/documentos/contratos', '1106'),
('1220', 'GESTION_GENERAL', 'DOC_INSTITUTIONAL', 'account_balance', '4', 'Documentos institucionales', '/documentos/institucionales', '1106'),
('1221', 'GESTION_GENERAL', 'DOC_DOWNLOADS', 'download', '5', 'Descargas', '/documentos/descargas', '1106'),

-- =========================
-- NIVEL 3 - CURSOS
-- =========================
('1222', 'ACADEMICO', 'COURSES', 'menu_book', '1', 'Cursos', '/academico/cursos', '1107'),
('1223', 'ACADEMICO', 'GRADES_LEVEL', 'school', '2', 'Grados', '/academico/grados', '1107'),
('1224', 'ACADEMICO', 'SECTIONS', 'view_module', '3', 'Secciones', '/academico/secciones', '1107'),
('1225', 'ACADEMICO', 'CLASSROOMS', 'meeting_room', '4', 'Aulas', '/academico/aulas', '1107'),
('1226', 'ACADEMICO', 'SUBJECTS', 'auto_stories', '5', 'Asignaturas', '/academico/asignaturas', '1107'),
('1227', 'ACADEMICO', 'CLASS_SCHEDULE', 'schedule', '6', 'Horarios de clases', '/academico/horarios', '1107'),
('1228', 'ACADEMICO', 'ACADEMIC_CALENDAR', 'calendar_month', '7', 'Calendario académico', '/academico/calendario', '1107'),

-- =========================
-- NIVEL 3 - MATRÍCULA
-- =========================
('1229', 'ACADEMICO', 'ENROLL_NEW', 'person_add', '1', 'Nueva matrícula', '/matricula/nueva', '1108'),
('1230', 'ACADEMICO', 'ENROLLED_STUDENTS', 'groups', '2', 'Estudiantes matriculados', '/matricula/lista', '1108'),
('1231', 'ACADEMICO', 'ENROLL_RENEW', 'autorenew', '3', 'Renovación de matrícula', '/matricula/renovacion', '1108'),
('1232', 'ACADEMICO', 'TRANSFERS', 'swap_horiz', '4', 'Traslados', '/matricula/traslados', '1108'),
('1233', 'ACADEMICO', 'WITHDRAWALS', 'exit_to_app', '5', 'Retiros', '/matricula/retiros', '1108'),
('1234', 'ACADEMICO', 'ENROLL_HISTORY', 'history', '6', 'Historial de matrícula', '/matricula/historial', '1108'),

-- =========================
-- NIVEL 3 - ASISTENCIA
-- =========================
('1235', 'ACADEMICO', 'ATTENDANCE_REGISTER', 'edit_calendar', '1', 'Registrar asistencia', '/asistencia/registrar', '1109'),
('1236', 'ACADEMICO', 'ATTENDANCE_BY_COURSE', 'class', '2', 'Asistencia por curso', '/asistencia/curso', '1109'),
('1237', 'ACADEMICO', 'STAFF_ATTENDANCE_ACAD', 'badge', '3', 'Asistencia del personal', '/asistencia/personal', '1109'),
('1238', 'ACADEMICO', 'ABSENCE_JUSTIFY', 'fact_check', '4', 'Justificación de faltas', '/asistencia/justificacion', '1109'),
('1239', 'ACADEMICO', 'ATTENDANCE_REPORT', 'bar_chart', '5', 'Reporte de asistencia', '/asistencia/reporte', '1109'),
('1240', 'ACADEMICO', 'ATTENDANCE_ALERTS', 'notification_important', '6', 'Alertas de inasistencia', '/asistencia/alertas', '1109'),

-- =========================
-- NIVEL 3 - NOTAS
-- =========================
('1241', 'ACADEMICO', 'GRADE_ENTRY', 'edit', '1', 'Registro de notas', '/notas/registro', '1110'),
('1242', 'ACADEMICO', 'EVALUATION_TYPES', 'rule', '2', 'Tipos de evaluación', '/notas/tipos', '1110'),
('1243', 'ACADEMICO', 'COMPETENCIES', 'psychology', '3', 'Competencias / capacidades', '/notas/competencias', '1110'),
('1244', 'ACADEMICO', 'AVERAGES', 'calculate', '4', 'Promedios automáticos', '/notas/promedios', '1110'),
('1245', 'ACADEMICO', 'REPORT_CARDS', 'description', '5', 'Libretas (boletas)', '/notas/libretas', '1110'),
('1246', 'ACADEMICO', 'TEACHER_OBSERVATIONS', 'rate_review', '6', 'Observaciones del docente', '/notas/observaciones', '1110'),

-- =========================
-- NIVEL 3 - SEGUIMIENTO ACADÉMICO
-- =========================
('1247', 'ACADEMICO', 'AT_RISK', 'warning', '1', 'Estudiantes en riesgo', '/seguimiento/riesgo', '1111'),
('1248', 'ACADEMICO', 'ALERTS', 'notifications', '2', 'Alertas automáticas', '/seguimiento/alertas', '1111'),
('1249', 'ACADEMICO', 'EVOLUTION', 'trending_up', '3', 'Evolución del estudiante', '/seguimiento/evolucion', '1111'),
('1250', 'ACADEMICO', 'COMPARISON', 'compare', '4', 'Comparativa por cursos', '/seguimiento/comparativa', '1111'),
('1251', 'ACADEMICO', 'RECOMMENDATIONS', 'lightbulb', '5', 'Recomendaciones', '/seguimiento/recomendaciones', '1111'),

-- =========================
-- NIVEL 3 - TUTORÍA
-- =========================
('1252', 'ACADEMICO', 'TUTOR_ASSIGN', 'person_add', '1', 'Asignación de tutor', '/tutoria/asignacion', '1112'),
('1253', 'ACADEMICO', 'INDIVIDUAL_TRACK', 'person_search', '2', 'Seguimiento individual', '/tutoria/seguimiento', '1112'),
('1254', 'ACADEMICO', 'PARENT_MEETINGS', 'groups', '3', 'Reuniones con padres', '/tutoria/reuniones', '1112'),
('1255', 'ACADEMICO', 'PSYCHO_OBS', 'psychology', '4', 'Observaciones psicopedagógicas', '/tutoria/observaciones', '1112'),
('1256', 'ACADEMICO', 'TUTOR_HISTORY', 'history', '5', 'Historial de tutoría', '/tutoria/historial', '1112'),

-- =========================
-- NIVEL 3 - HORAS DOCENTES
-- =========================
('1257', 'ACADEMICO', 'WORKLOAD', 'schedule', '1', 'Carga horaria', '/docentes/carga', '1113'),
('1258', 'ACADEMICO', 'HOURS_TAUGHT', 'task', '2', 'Horas dictadas', '/docentes/dictadas', '1113'),
('1259', 'ACADEMICO', 'HOURS_PENDING', 'pending_actions', '3', 'Horas pendientes', '/docentes/pendientes', '1113'),
('1260', 'ACADEMICO', 'HOURS_REPORT', 'assessment', '4', 'Reporte de horas', '/docentes/reporte', '1113'),

-- =========================
-- NIVEL 3 - AGENDA
-- =========================
('1261', 'ESCOLAR', 'CALENDAR_VIEW', 'calendar_view_month', '1', 'Vista calendario', '/agenda/calendario', '1114'),
('1262', 'ESCOLAR', 'EVENTS_AGENDA', 'event', '2', 'Eventos', '/agenda/eventos', '1114'),
('1263', 'ESCOLAR', 'EVALUATIONS', 'grading', '3', 'Evaluaciones', '/agenda/evaluaciones', '1114'),
('1264', 'ESCOLAR', 'WORKSHOPS_AGENDA', 'groups', '4', 'Talleres', '/agenda/talleres', '1114'),
('1265', 'ESCOLAR', 'REMINDERS', 'notifications', '5', 'Recordatorios', '/agenda/recordatorios', '1114'),

-- =========================
-- NIVEL 3 - EVENTOS
-- =========================

('1266', 'ESCOLAR', 'EVENT_CALENDAR', 'calendar_today', '1', 'Calendario de eventos', '/eventos/calendario', '1115'),
('1267', 'ESCOLAR', 'INSTITUTIONAL_EVENTS', 'account_balance', '2', 'Eventos institucionales', '/eventos/institucionales', '1115'),
('1268', 'ESCOLAR', 'SCHOOL_ACTIVITIES', 'celebration', '3', 'Actividades escolares', '/eventos/actividades', '1115'),
('1269', 'ESCOLAR', 'CALLS', 'campaign', '4', 'Convocatorias', '/eventos/convocatorias', '1115'),
('1270', 'ESCOLAR', 'PARTICIPATION', 'how_to_reg', '5', 'Registro de participación', '/eventos/participacion', '1115'),

-- =========================
-- NIVEL 3 - Talleres
-- =========================

('1271', 'ESCOLAR', 'WORKSHOP_LIST', 'list', '1', 'Lista de talleres', '/talleres/lista', '1116'),
('1272', 'ESCOLAR', 'WORKSHOP_ENROLL', 'person_add', '2', 'Inscripción de estudiantes', '/talleres/inscripcion', '1116'),
('1273', 'ESCOLAR', 'WORKSHOP_SCHEDULE', 'schedule', '3', 'Horarios de talleres', '/talleres/horarios', '1116'),

-- =========================
-- NIVEL 3 - DISCIPLINA
-- =========================
('1274', 'ESCOLAR', 'INCIDENTS', 'report', '1', 'Registro de incidencias', '/disciplina/incidencias', '1117'),
('1275', 'ESCOLAR', 'BEHAVIOR_REPORT', 'assessment', '2', 'Reportes de conducta', '/disciplina/reportes', '1117'),
('1276', 'ESCOLAR', 'SANCTIONS', 'gavel', '3', 'Sanciones', '/disciplina/sanciones', '1117'),
('1277', 'ESCOLAR', 'PARENT_MEETINGS_DISC', 'groups', '4', 'Citaciones a padres', '/disciplina/citaciones', '1117'),
('1278', 'ESCOLAR', 'CASE_TRACKING', 'track_changes', '5', 'Seguimiento de casos', '/disciplina/seguimiento', '1117'),


-- =========================
-- NIVEL 3 - Trámites académicos
-- =========================

('1279', 'PROCESOS', 'REQUESTS', 'assignment', '1', 'Solicitudes', NULL, '1118'),
('1280', 'PROCESOS', 'PROCESS_STATUS', 'hourglass_top', '2', 'Estado de trámites', '/tramites/estado', '1118'),
('1281', 'PROCESOS', 'REQUEST_HISTORY', 'history', '3', 'Historial de solicitudes', '/tramites/historial', '1118'),

-- =========================
-- NIVEL 4 - Solicitudes
-- =========================
('1414', 'PROCESOS', 'CERTIFICATES', 'verified', '1', 'Certificados de estudio', '/tramites/certificados', '1279'),
('1415', 'PROCESOS', 'CONSTANCIES', 'description', '2', 'Constancias', '/tramites/constancias', '1279'),
('1416', 'PROCESOS', 'TRANSFERS_DOC', 'swap_horiz', '3', 'Traslados', '/tramites/traslados', '1279'),

-- =========================
-- NIVEL 3 - Aprobaciones
-- =========================

('1285', 'PROCESOS', 'APPROVE_REQUESTS', 'task_alt', '1', 'Aprobación de trámites', '/aprobaciones/tramites', '1119'),
('1286', 'PROCESOS', 'PAYMENT_VALIDATION', 'payments', '2', 'Validación de pagos', '/aprobaciones/pagos', '1119'),
('1287', 'PROCESOS', 'AUTHORIZATIONS', 'verified_user', '3', 'Autorizaciones', '/aprobaciones/autorizaciones', '1119'),
('1288', 'PROCESOS', 'APPROVAL_STATUS', 'rule', '4', 'Estados (pendiente, aprobado, rechazado)', '/aprobaciones/estado', '1119'),


-- =========================
-- NIVEL 3 - Flujos automáticos
-- =========================

('1289', 'PROCESOS', 'ENROLLMENT_FLOW', 'sync_alt', '1', 'Flujo de matrícula', '/flujos/matricula', '1120'),
('1290', 'PROCESOS', 'PAYMENT_FLOW', 'paid', '2', 'Flujo de pagos', '/flujos/pagos', '1120'),
('1291', 'PROCESOS', 'PROCESS_FLOW', 'account_tree', '3', 'Flujo de trámites', '/flujos/tramites', '1120'),
('1292', 'PROCESOS', 'ACADEMIC_FLOW', 'school', '4', 'Flujo académico', '/flujos/academico', '1120'),

-- =========================
-- NIVEL 3 - Planilla
-- =========================
('1293', 'FINANZAS', 'SALARIES', 'payments', '1', 'Sueldos', '/planilla/sueldos', '1126'),
('1294', 'FINANZAS', 'STAFF_PAYMENTS', 'paid', '2', 'Pagos al personal', '/planilla/pagos', '1126'),
('1295', 'FINANZAS', 'DEDUCTIONS', 'remove_circle', '3', 'Descuentos', '/planilla/descuentos', '1126'),
('1296', 'FINANZAS', 'PAYROLL_HISTORY', 'history', '4', 'Historial', '/planilla/historial', '1126'),

-- =========================
-- NIVEL 3 - CARNET
-- =========================

('1297', 'SEGURIDAD', 'QR_GENERATION', 'qr_code', '1', 'Generación de QR', '/seguridad/carnet/qr', '1129'),
('1298', 'SEGURIDAD', 'IDENTITY_VALIDATION', 'verified_user', '2', 'Validación de identidad', '/seguridad/carnet/validacion', '1129'),
('1299', 'SEGURIDAD', 'ACCESS_USAGE', 'touch_app', '3', 'Uso en acceso', '/seguridad/carnet/uso', '1129'),
-- =========================
-- NIVEL 3 - NOTIFICACIONES
-- =========================

('1300', 'COMUNICACION', 'INBOX', 'inbox', '1', 'Bandeja', '/notificaciones/bandeja', '1135'),
('1301', 'COMUNICACION', 'NOTIFICATION_HISTORY', 'history', '2', 'Historial', '/notificaciones/historial', '1135'),
('1302', 'COMUNICACION', 'NOTIFICATION_SETTINGS', 'settings', '3', 'Configuración', '/notificaciones/configuracion', '1135'),
-- =========================
-- NIVEL 3 - dashbvoard
-- =========================
('1303', 'ANALITICA', 'KPIS', 'insights', '1', 'KPIs', '/analitica/kpis', '1141'),
('1304', 'ANALITICA', 'STUDENT_ANALYTICS', 'school', '2', 'Estudiantes', '/analitica/estudiantes', '1141'),
('1305', 'ANALITICA', 'TEACHER_ANALYTICS', 'badge', '3', 'Profesores', '/analitica/profesores', '1141'),
('1306', 'ANALITICA', 'INCOME_ANALYTICS', 'payments', '4', 'Ingresos', '/analitica/ingresos', '1141'),
('1307', 'ANALITICA', 'ATTENDANCE_ANALYTICS', 'fact_check', '5', 'Asistencia', '/analitica/asistencia', '1141'),


-- =========================
-- NIVEL 3 - dashbvoard
-- =========================

('1308', 'CONFIGURACION', 'TEMPLATE_DOCUMENTS', 'description', '1', 'Plantillas de documentos', '/configuracion/plantillas/documentos', '1161'),
('1309', 'CONFIGURACION', 'TEMPLATE_REPORTS', 'assessment', '2', 'Plantillas de reportes', '/configuracion/plantillas/reportes', '1161'),
('1310', 'CONFIGURACION', 'TEMPLATE_NOTIFICATIONS', 'notifications', '3', 'Plantillas de notificaciones', '/configuracion/plantillas/notificaciones', '1161'),


('1315', 'EXTRA', 'BOOKS', 'menu_book', '1', 'Libros', '/biblioteca/libros', '1164'),
('1316', 'EXTRA', 'LOANS', 'assignment_return', '2', 'Préstamos', '/biblioteca/prestamos', '1164'),
('1317', 'EXTRA', 'RETURNS', 'assignment_turned_in', '3', 'Devoluciones', '/biblioteca/devoluciones', '1164'),

('1318', 'EXTRA', 'ROUTES', 'alt_route', '1', 'Rutas', '/transporte/rutas', '1165'),
('1319', 'EXTRA', 'VEHICLES', 'directions_bus', '2', 'Vehículos', '/transporte/vehiculos', '1165'),
('1320', 'EXTRA', 'STUDENT_ASSIGNMENT', 'group_add', '3', 'Asignación de estudiantes', '/transporte/asignacion', '1165'),
('1321', 'EXTRA', 'DAILY_MENU', 'restaurant_menu', '1', 'Menú diario', '/comedor/menu', '1166'),
('1322', 'EXTRA', 'CONSUMPTION', 'fastfood', '2', 'Control de consumo', '/comedor/consumo', '1166'),
('1323', 'EXTRA', 'DINING_PAYMENTS', 'payments', '3', 'Pagos', '/comedor/pagos', '1166'),

('1324', 'EXTRA', 'MEDICAL_RECORD', 'folder_shared', '1', 'Ficha médica', '/enfermeria/ficha', '1167'),
('1325', 'EXTRA', 'ATTENTIONS', 'healing', '2', 'Atenciones', '/enfermeria/atenciones', '1167'),
('1326', 'EXTRA', 'MEDICINES', 'medication', '3', 'Medicamentos', '/enfermeria/medicamentos', '1167'),
('1327', 'EXTRA', 'TASKS', 'assignment', '1', 'Tareas', '/virtual/tareas', '1168'),
('1328', 'EXTRA', 'MATERIALS', 'folder', '2', 'Materiales', '/virtual/materiales', '1168'),
('1329', 'EXTRA', 'ONLINE_EXAMS', 'quiz', '3', 'Evaluaciones online', '/virtual/evaluaciones', '1168');