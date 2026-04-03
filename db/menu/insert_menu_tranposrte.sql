INSERT INTO menu (id, category, code, icon, menu_order, name, route, parent_id) VALUES

-- =========================
-- ROOT TRANSPORTE
-- =========================
('9000','TRANSPORTE','ROOT','directions_bus','1','Transporte Escolar',NULL,NULL),

-- =========================
-- DASHBOARD
-- =========================
('9001','TRANSPORTE','DASHBOARD','dashboard','1','Dashboard',NULL,'9000'),
('9002','TRANSPORTE','ACTIVE_ROUTES','alt_route','1','Rutas activas','/transporte/dashboard/rutas','9001'),
('9003','TRANSPORTE','STUDENTS_ON_ROUTE','groups','2','Alumnos transportados','/transporte/dashboard/estudiantes','9001'),
('9004','TRANSPORTE','ALERTS','warning','3','Alertas','/transporte/dashboard/alertas','9001'),
('9005','TRANSPORTE','ACTIVITY','history','4','Actividad reciente','/transporte/dashboard/actividad','9001'),

-- =========================
-- RUTAS
-- =========================
('9010','TRANSPORTE','ROUTES','alt_route','2','Rutas',NULL,'9000'),
('9011','TRANSPORTE','ROUTE_LIST','list','1','Lista de rutas','/transporte/rutas','9010'),
('9012','TRANSPORTE','ROUTE_DETAIL','visibility','2','Detalle','/transporte/rutas/detalle','9010'),
('9013','TRANSPORTE','STOPS','place','3','Paraderos','/transporte/rutas/paraderos','9010'),
('9014','TRANSPORTE','SCHEDULES','schedule','4','Horarios','/transporte/rutas/horarios','9010'),

-- =========================
-- ESTUDIANTES
-- =========================
('9020','TRANSPORTE','STUDENTS','school','3','Estudiantes',NULL,'9000'),
('9021','TRANSPORTE','STUDENT_LIST','list','1','Lista','/transporte/estudiantes','9020'),
('9022','TRANSPORTE','ASSIGNED_ROUTE','alt_route','2','Ruta asignada','/transporte/estudiantes/ruta','9020'),
('9023','TRANSPORTE','STUDENT_STOP','place','3','Paradero','/transporte/estudiantes/paradero','9020'),

-- =========================
-- CONTROL DE VIAJE
-- =========================
('9030','TRANSPORTE','TRAVEL_CONTROL','commute','4','Control de Viaje',NULL,'9000'),
('9031','TRANSPORTE','BOARDING','login','1','Registrar subida','/transporte/viaje/subida','9030'),
('9032','TRANSPORTE','DROPOFF','logout','2','Registrar bajada','/transporte/viaje/bajada','9030'),
('9033','TRANSPORTE','STATUS','verified','3','Estado del estudiante','/transporte/viaje/estado','9030'),
('9034','TRANSPORTE','CONTROL_BY_ROUTE','route','4','Control por ruta','/transporte/viaje/ruta','9030'),

-- =========================
-- APODERADOS
-- =========================
('9040','TRANSPORTE','PARENTS','people','5','Apoderados',NULL,'9000'),
('9041','TRANSPORTE','CONTACT','contact_phone','1','Contacto','/transporte/apoderados/contacto','9040'),
('9042','TRANSPORTE','AUTHORIZED','verified_user','2','Autorizados','/transporte/apoderados/autorizados','9040'),

-- =========================
-- INCIDENCIAS
-- =========================
('9050','TRANSPORTE','INCIDENTS','report','6','Incidencias',NULL,'9000'),
('9051','TRANSPORTE','INCIDENT_CREATE','add_alert','1','Registrar','/transporte/incidencias/registrar','9050'),
('9052','TRANSPORTE','INCIDENT_ALERTS','notification_important','2','Alertas','/transporte/incidencias/alertas','9050'),
('9053','TRANSPORTE','INCIDENT_HISTORY','history','3','Historial','/transporte/incidencias/historial','9050'),

-- =========================
-- PROGRAMACIÓN
-- =========================
('9060','TRANSPORTE','PLANNING','calendar_month','7','Calendario / Programación',NULL,'9000'),
('9061','TRANSPORTE','TODAY_ROUTES','today','1','Rutas del día','/transporte/programacion/rutas','9060'),
('9062','TRANSPORTE','CHANGES','update','2','Cambios','/transporte/programacion/cambios','9060'),
('9063','TRANSPORTE','EVENTS','event','3','Eventos','/transporte/programacion/eventos','9060'),

-- =========================
-- REPORTES
-- =========================
('9070','TRANSPORTE','REPORTS','assessment','8','Reportes',NULL,'9000'),
('9071','TRANSPORTE','REP_ATTENDANCE','fact_check','1','Asistencia transporte','/transporte/reportes/asistencia','9070'),
('9072','TRANSPORTE','REP_TRAVEL_HISTORY','history','2','Historial viajes','/transporte/reportes/viajes','9070'),
('9073','TRANSPORTE','REP_INCIDENTS','report','3','Incidencias','/transporte/reportes/incidencias','9070');