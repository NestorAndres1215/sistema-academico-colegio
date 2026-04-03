INSERT INTO menu (id, category, code, icon, menu_order, name, route, parent_id) VALUES

-- =========================
-- ROOT SECRETARIA
-- =========================
('4000','SECRETARIA','ROOT','support_agent','1','Secretaría',NULL,NULL),

-- =========================
-- DASHBOARD
-- =========================
('4001','SECRETARIA','DASHBOARD','dashboard','1','Dashboard',NULL,'4000'),
('4002','SECRETARIA','ALERTS_PROC','warning','1','Alertas de trámites','/secretaria/alertas','4001'),
('4003','SECRETARIA','ACTIVITY','history','2','Actividad reciente','/secretaria/actividad','4001'),
('4004','SECRETARIA','NOTIFICATIONS','notifications','3','Notificaciones','/secretaria/notificaciones','4001'),

-- =========================
-- TRÁMITES Y PROCESOS
-- =========================
('4010','SECRETARIA','PROCESSES','description','2','Trámites y Procesos',NULL,'4000'),

('4011','SECRETARIA','REQUESTS','assignment','1','Solicitudes',NULL,'4010'),
('4012','SECRETARIA','CERTIFICATES','verified','1','Certificados','/secretaria/tramites/certificados','4011'),
('4013','SECRETARIA','CONSTANCIES','description','2','Constancias','/secretaria/tramites/constancias','4011'),
('4014','SECRETARIA','TRANSFERS','swap_horiz','3','Traslados','/secretaria/tramites/traslados','4011'),

('4015','SECRETARIA','STATUS','hourglass_top','2','Estado de trámites','/secretaria/tramites/estado','4010'),
('4016','SECRETARIA','HISTORY','history','3','Historial de solicitudes','/secretaria/tramites/historial','4010'),
('4017','SECRETARIA','FLOW','alt_route','4','Flujo de aprobación','/secretaria/tramites/flujos','4010'),

-- =========================
-- MATRÍCULA
-- =========================
('4020','SECRETARIA','ENROLLMENT','how_to_reg','3','Matrícula',NULL,'4000'),
('4021','SECRETARIA','NEW_ENROLL','person_add','1','Nueva matrícula','/secretaria/matricula/nueva','4020'),
('4022','SECRETARIA','ENROLLED','groups','2','Estudiantes matriculados','/secretaria/matricula/lista','4020'),
('4023','SECRETARIA','RENEW','autorenew','3','Renovación','/secretaria/matricula/renovacion','4020'),
('4024','SECRETARIA','TRANSFER','swap_horiz','4','Traslados','/secretaria/matricula/traslados','4020'),
('4025','SECRETARIA','WITHDRAW','exit_to_app','5','Retiros','/secretaria/matricula/retiros','4020'),
('4026','SECRETARIA','ENROLL_HISTORY','history','6','Historial','/secretaria/matricula/historial','4020'),

-- =========================
-- ESTUDIANTES
-- =========================
('4030','SECRETARIA','STUDENTS','school','4','Gestión de Estudiantes',NULL,'4000'),
('4031','SECRETARIA','STUDENT_LIST','list','1','Lista de estudiantes','/secretaria/estudiantes','4030'),
('4032','SECRETARIA','STUDENT_PROFILE','person','2','Ficha / Datos','/secretaria/estudiantes/datos','4030'),
('4033','SECRETARIA','STUDENT_STATUS','toggle_on','3','Estado','/secretaria/estudiantes/estado','4030'),

-- =========================
-- APODERADOS
-- =========================
('4040','SECRETARIA','PARENTS','people','5','Gestión de Apoderados',NULL,'4000'),
('4041','SECRETARIA','PARENT_LIST','list','1','Lista','/secretaria/apoderados','4040'),
('4042','SECRETARIA','PARENT_CONTACT','contact_phone','2','Datos / contacto','/secretaria/apoderados/contacto','4040'),
('4043','SECRETARIA','RELATION','link','3','Relación estudiante–apoderado','/secretaria/apoderados/relacion','4040'),

-- =========================
-- AGENDA / CALENDARIO
-- =========================
('4050','SECRETARIA','AGENDA','calendar_month','6','Calendario / Agenda',NULL,'4000'),

('4051','SECRETARIA','EVENTS','event','1','Eventos',NULL,'4050'),
('4052','SECRETARIA','EVENT_LIST','event_available','1','Eventos institucionales','/secretaria/eventos','4051'),
('4053','SECRETARIA','WORKSHOPS','groups','2','Talleres','/secretaria/eventos/talleres','4051'),

('4054','SECRETARIA','MEETINGS','groups','2','Reuniones','/secretaria/reuniones','4050'),

('4055','SECRETARIA','REMINDERS','notifications','3','Recordatorios',NULL,'4050'),
('4056','SECRETARIA','REMINDER_EVENTS','notification_important','1','Alertas','/secretaria/recordatorios','4055'),

('4057','SECRETARIA','SCHEDULE','schedule','4','Horarios importantes','/secretaria/horarios','4050'),

-- =========================
-- ASISTENCIA
-- =========================
('4060','SECRETARIA','ATTENDANCE','fact_check','7','Asistencia',NULL,'4000'),
('4061','SECRETARIA','STAFF_ATT','badge','1','Personal (solo lectura)','/secretaria/asistencia/personal','4060'),
('4062','SECRETARIA','ATT_ALERTS','warning','2','Alertas de inasistencia','/secretaria/asistencia/alertas','4060'),

-- =========================
-- REPORTES
-- =========================
('4070','SECRETARIA','REPORTS','assessment','8','Reportes',NULL,'4000'),
('4071','SECRETARIA','REP_PROC','description','1','Trámites','/secretaria/reportes/tramites','4070'),
('4072','SECRETARIA','REP_STUD','school','2','Estudiantes','/secretaria/reportes/estudiantes','4070'),
('4073','SECRETARIA','REP_PARENT','people','3','Apoderados','/secretaria/reportes/apoderados','4070'),

-- =========================
-- COMUNICACIÓN
-- =========================
('4080','SECRETARIA','COMM','campaign','9','Comunicación',NULL,'4000'),
('4081','SECRETARIA','INTERNAL_NOT','notifications','1','Notificaciones internas','/secretaria/comunicacion/notificaciones','4080'),
('4082','SECRETARIA','MESSAGES','mail','2','Mensajes a padres / personal','/secretaria/comunicacion/mensajes','4080'),
('4083','SECRETARIA','CIRCULARS','description','3','Circulares / anuncios','/secretaria/comunicacion/circulares','4080');