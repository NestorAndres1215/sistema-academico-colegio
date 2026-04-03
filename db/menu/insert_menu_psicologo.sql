INSERT INTO menu (id, category, code, icon, menu_order, name, route, parent_id) VALUES

-- =========================
-- ROOT PSICOLOGÍA
-- =========================
('7000','PSICOLOGIA','ROOT','psychology','1','Psicología',NULL,NULL),

-- =========================
-- DASHBOARD
-- =========================
('7001','PSICOLOGIA','DASHBOARD','dashboard','1','Dashboard',NULL,'7000'),
('7002','PSICOLOGIA','ACTIVE_CASES','assignment','1','Casos activos','/psicologia/dashboard/casos','7001'),
('7003','PSICOLOGIA','ALERTS','warning','2','Alertas','/psicologia/dashboard/alertas','7001'),
('7004','PSICOLOGIA','TODAY_APPOINTMENTS','event','3','Citas del día','/psicologia/dashboard/citas','7001'),
('7005','PSICOLOGIA','ACTIVITY','history','4','Actividad reciente','/psicologia/dashboard/actividad','7001'),

-- =========================
-- ESTUDIANTES
-- =========================
('7010','PSICOLOGIA','STUDENTS','school','2','Estudiantes',NULL,'7000'),
('7011','PSICOLOGIA','STUDENT_LIST','list','1','Lista','/psicologia/estudiantes','7010'),
('7012','PSICOLOGIA','PSY_PROFILE','person','2','Perfil psicológico','/psicologia/estudiantes/perfil','7010'),
('7013','PSICOLOGIA','PSY_HISTORY','history','3','Historial','/psicologia/estudiantes/historial','7010'),

-- =========================
-- FICHA PSICOLÓGICA
-- =========================
('7020','PSICOLOGIA','PSY_FILE','folder_shared','3','Ficha Psicológica',NULL,'7000'),
('7021','PSICOLOGIA','INITIAL_EVAL','assignment','1','Evaluación inicial','/psicologia/ficha/evaluacion','7020'),
('7022','PSICOLOGIA','OBSERVATIONS','visibility','2','Observaciones','/psicologia/ficha/observaciones','7020'),
('7023','PSICOLOGIA','DIAGNOSIS','biotech','3','Diagnóstico','/psicologia/ficha/diagnostico','7020'),
('7024','PSICOLOGIA','RISK_FACTORS','warning','4','Factores de riesgo','/psicologia/ficha/riesgos','7020'),
('7025','PSICOLOGIA','INTERVENTION_PLAN','task','5','Plan de intervención','/psicologia/ficha/plan','7020'),
('7026','PSICOLOGIA','FILE_HISTORY','history','6','Historial','/psicologia/ficha/historial','7020'),

-- =========================
-- SESIONES
-- =========================
('7030','PSICOLOGIA','SESSIONS','healing','4','Atenciones / Sesiones',NULL,'7000'),
('7031','PSICOLOGIA','SESSION_CREATE','add','1','Registrar sesión','/psicologia/sesiones/registrar','7030'),
('7032','PSICOLOGIA','SESSION_TYPE','category','2','Tipo de sesión','/psicologia/sesiones/tipo','7030'),
('7033','PSICOLOGIA','SESSION_NOTES','edit_note','3','Observaciones','/psicologia/sesiones/observaciones','7030'),
('7034','PSICOLOGIA','SESSION_HISTORY','history','4','Historial','/psicologia/sesiones/historial','7030'),

-- =========================
-- AGENDA
-- =========================
('7040','PSICOLOGIA','AGENDA','calendar_month','5','Citas / Agenda',NULL,'7000'),
('7041','PSICOLOGIA','APPOINTMENT_CREATE','event_available','1','Programar cita','/psicologia/agenda/programar','7040'),
('7042','PSICOLOGIA','CALENDAR','calendar_view_month','2','Calendario','/psicologia/agenda/calendario','7040'),
('7043','PSICOLOGIA','FOLLOW_UP','track_changes','3','Seguimiento','/psicologia/agenda/seguimiento','7040'),
('7044','PSICOLOGIA','REMINDERS','notifications','4','Recordatorios','/psicologia/agenda/recordatorios','7040'),

-- =========================
-- CASOS
-- =========================
('7050','PSICOLOGIA','CASES','assignment_late','6','Casos y Seguimiento',NULL,'7000'),
('7051','PSICOLOGIA','ACTIVE_CASES_LIST','list','1','Casos activos','/psicologia/casos/activos','7050'),
('7052','PSICOLOGIA','RISK_LEVEL','priority_high','2','Nivel de riesgo','/psicologia/casos/riesgo','7050'),
('7053','PSICOLOGIA','EVOLUTION','trending_up','3','Evolución','/psicologia/casos/evolucion','7050'),
('7054','PSICOLOGIA','CASE_CLOSE','check_circle','4','Cierre de casos','/psicologia/casos/cierre','7050'),

-- =========================
-- APODERADOS
-- =========================
('7060','PSICOLOGIA','PARENTS','people','7','Apoderados',NULL,'7000'),
('7061','PSICOLOGIA','PARENT_CONTACT','contact_phone','1','Contacto','/psicologia/apoderados/contacto','7060'),
('7062','PSICOLOGIA','MEETINGS','groups','2','Reuniones','/psicologia/apoderados/reuniones','7060'),
('7063','PSICOLOGIA','PARENT_TRACK','track_changes','3','Seguimiento','/psicologia/apoderados/seguimiento','7060'),

-- =========================
-- COORDINACIÓN
-- =========================
('7070','PSICOLOGIA','COORDINATION','hub','8','Coordinación',NULL,'7000'),
('7071','PSICOLOGIA','TEACHER_COMM','forum','1','Comunicación con docentes','/psicologia/coordinacion/docentes','7070'),
('7072','PSICOLOGIA','RECOMMENDATIONS','lightbulb','2','Recomendaciones','/psicologia/coordinacion/recomendaciones','7070'),
('7073','PSICOLOGIA','DERIVATIONS','compare_arrows','3','Derivaciones','/psicologia/coordinacion/derivaciones','7070'),

-- =========================
-- REPORTES
-- =========================
('7080','PSICOLOGIA','REPORTS','assessment','9','Reportes',NULL,'7000'),
('7081','PSICOLOGIA','REP_STUDENT','person','1','Por estudiante','/psicologia/reportes/estudiante','7080'),
('7082','PSICOLOGIA','REP_CASES','assignment','2','Casos activos','/psicologia/reportes/casos','7080'),
('7083','PSICOLOGIA','REP_INDICATORS','insights','3','Indicadores','/psicologia/reportes/indicadores','7080');