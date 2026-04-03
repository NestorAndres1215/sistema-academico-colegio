INSERT INTO menu (id, category, code, icon, menu_order, name, route, parent_id) VALUES

-- =========================
-- ROOT BIBLIOTECARIO
-- =========================
('5000','BIBLIOTECA','ROOT','menu_book','1','Biblioteca',NULL,NULL),

-- =========================
-- DASHBOARD
-- =========================
('5001','BIBLIOTECA','DASHBOARD','dashboard','1','Dashboard',NULL,'5000'),
('5002','BIBLIOTECA','LOAN_SUMMARY','insights','1','Resumen de préstamos','/biblioteca/dashboard/resumen','5001'),
('5003','BIBLIOTECA','LATE_ALERTS','warning','2','Alertas de libros atrasados','/biblioteca/dashboard/atrasos','5001'),
('5004','BIBLIOTECA','ACTIVITY','history','3','Actividad reciente','/biblioteca/dashboard/actividad','5001'),

-- =========================
-- GESTIÓN DE LIBROS
-- =========================
('5010','BIBLIOTECA','BOOK_MANAGEMENT','menu_book','2','Gestión de Libros / Materiales',NULL,'5000'),
('5011','BIBLIOTECA','BOOK_LIST','list','1','Lista de libros','/biblioteca/libros','5010'),
('5012','BIBLIOTECA','BOOK_CREATE','library_add','2','Registrar libro','/biblioteca/libros/crear','5010'),
('5013','BIBLIOTECA','BOOK_UPDATE','edit','3','Actualizar datos','/biblioteca/libros/editar','5010'),
('5014','BIBLIOTECA','INVENTORY','inventory','4','Inventario','/biblioteca/libros/inventario','5010'),

-- =========================
-- PRÉSTAMOS
-- =========================
('5020','BIBLIOTECA','LOANS','assignment','3','Préstamos',NULL,'5000'),
('5021','BIBLIOTECA','LOAN_CREATE','assignment_turned_in','1','Registrar préstamo','/biblioteca/prestamos/registrar','5020'),
('5022','BIBLIOTECA','LOAN_ACTIVE','fact_check','2','Historial activo','/biblioteca/prestamos/activos','5020'),
('5023','BIBLIOTECA','LOAN_ALERTS','notification_important','3','Alertas atrasos','/biblioteca/prestamos/alertas','5020'),

-- =========================
-- DEVOLUCIONES
-- =========================
('5030','BIBLIOTECA','RETURNS','assignment_return','4','Devoluciones',NULL,'5000'),
('5031','BIBLIOTECA','RETURN_CREATE','keyboard_return','1','Registrar devolución','/biblioteca/devoluciones/registrar','5030'),
('5032','BIBLIOTECA','RETURN_STATUS','update','2','Actualizar estado','/biblioteca/devoluciones/estado','5030'),
('5033','BIBLIOTECA','DAMAGE_ALERTS','report_problem','3','Alertas daños','/biblioteca/devoluciones/danios','5030'),

-- =========================
-- AGENDA
-- =========================
('5040','BIBLIOTECA','AGENDA','calendar_month','5','Calendario / Agenda',NULL,'5000'),
('5041','BIBLIOTECA','RESERVATIONS','event_available','1','Reservas','/biblioteca/agenda/reservas','5040'),
('5042','BIBLIOTECA','EVENTS','event','2','Eventos','/biblioteca/agenda/eventos','5040'),

-- =========================
-- USUARIOS
-- =========================
('5050','BIBLIOTECA','USERS','people','6','Usuarios / Beneficiarios',NULL,'5000'),
('5051','BIBLIOTECA','USER_LIST','list','1','Lista de usuarios','/biblioteca/usuarios','5050'),
('5052','BIBLIOTECA','USER_HISTORY','history','2','Historial de préstamos','/biblioteca/usuarios/historial','5050'),

-- =========================
-- REPORTES
-- =========================
('5060','BIBLIOTECA','REPORTS','assessment','7','Reportes',NULL,'5000'),
('5061','BIBLIOTECA','REP_LOANS','assignment','1','Préstamos y devoluciones','/biblioteca/reportes/prestamos','5060'),
('5062','BIBLIOTECA','REP_TOP','trending_up','2','Libros más solicitados','/biblioteca/reportes/top','5060'),
('5063','BIBLIOTECA','REP_LATE','warning','3','Libros atrasados / perdidos','/biblioteca/reportes/atrasos','5060'),
('5064','BIBLIOTECA','REP_INV','inventory','4','Inventario','/biblioteca/reportes/inventario','5060');