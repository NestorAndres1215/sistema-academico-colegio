INSERT INTO menu (id, category, code, icon, menu_order, name, route, parent_id) VALUES

-- =========================
-- ROOT COMEDOR
-- =========================
('8000','COMEDOR','ROOT','restaurant','1','Comedor',NULL,NULL),

-- =========================
-- DASHBOARD
-- =========================
('8001','COMEDOR','DASHBOARD','dashboard','1','Dashboard',NULL,'8000'),
('8002','COMEDOR','DAY_SUMMARY','insights','1','Resumen del día','/comedor/dashboard/resumen','8001'),
('8003','COMEDOR','FOOD_ALERTS','warning','2','Alertas alimentarias','/comedor/dashboard/alertas','8001'),
('8004','COMEDOR','TODAY_MENU','restaurant_menu','3','Menú del día','/comedor/dashboard/menu','8001'),
('8005','COMEDOR','ACTIVITY','history','4','Actividad reciente','/comedor/dashboard/actividad','8001'),

-- =========================
-- MENÚ
-- =========================
('8010','COMEDOR','MENU_MODULE','restaurant_menu','2','Menú Diario',NULL,'8000'),
('8011','COMEDOR','MENU_DAY','today','1','Menú del día','/comedor/menu/dia','8010'),
('8012','COMEDOR','MENU_WEEK','date_range','2','Menú semanal','/comedor/menu/semanal','8010'),
('8013','COMEDOR','MENU_TYPES','category','3','Tipos de menú','/comedor/menu/tipos','8010'),
('8014','COMEDOR','MENU_UPDATE','edit','4','Actualizar menú','/comedor/menu/actualizar','8010'),

-- =========================
-- ESTUDIANTES
-- =========================
('8020','COMEDOR','STUDENTS','school','3','Estudiantes',NULL,'8000'),
('8021','COMEDOR','STUDENT_LIST','list','1','Lista','/comedor/estudiantes','8020'),
('8022','COMEDOR','STUDENT_SEARCH','search','2','Buscar','/comedor/estudiantes/buscar','8020'),
('8023','COMEDOR','FOOD_RESTRICTIONS','no_food','3','Restricciones alimentarias','/comedor/estudiantes/restricciones','8020'),

-- =========================
-- CONSUMO
-- =========================
('8030','COMEDOR','CONSUMPTION','restaurant','4','Control de Consumo',NULL,'8000'),
('8031','COMEDOR','CONSUME_REGISTER','add_shopping_cart','1','Registrar consumo','/comedor/consumo/registrar','8030'),
('8032','COMEDOR','BY_SHIFT','schedule','2','Por turno','/comedor/consumo/turno','8030'),
('8033','COMEDOR','CONSUME_HISTORY','history','3','Historial','/comedor/consumo/historial','8030'),

-- =========================
-- ALERTAS
-- =========================
('8040','COMEDOR','ALERTS_MODULE','warning','5','Alertas Alimentarias',NULL,'8000'),
('8041','COMEDOR','ALLERGIES','report_problem','1','Alergias','/comedor/alertas/alergias','8040'),
('8042','COMEDOR','SPECIAL_DIETS','eco','2','Dietas especiales','/comedor/alertas/dietas','8040'),
('8043','COMEDOR','MEDICAL_RESTRICTIONS','healing','3','Restricciones médicas','/comedor/alertas/restricciones','8040'),

-- =========================
-- INVENTARIO
-- =========================
('8050','COMEDOR','INVENTORY','inventory','6','Inventario',NULL,'8000'),
('8051','COMEDOR','STOCK','inventory_2','1','Stock','/comedor/inventario/stock','8050'),
('8052','COMEDOR','INPUT_OUTPUT','swap_vert','2','Entradas / salidas','/comedor/inventario/movimientos','8050'),
('8053','COMEDOR','INV_ALERTS','notification_important','3','Alertas','/comedor/inventario/alertas','8050'),

-- =========================
-- PLANIFICACIÓN
-- =========================
('8060','COMEDOR','PLANNING','calendar_month','7','Calendario / Planificación',NULL,'8000'),
('8061','COMEDOR','WEEK_MENU','date_range','1','Menú semanal','/comedor/planificacion/semanal','8060'),
('8062','COMEDOR','MONTH_PLAN','calendar_today','2','Plan mensual','/comedor/planificacion/mensual','8060'),
('8063','COMEDOR','EVENTS','event','3','Eventos','/comedor/planificacion/eventos','8060'),

-- =========================
-- REPORTES
-- =========================
('8070','COMEDOR','REPORTS','assessment','8','Reportes',NULL,'8000'),
('8071','COMEDOR','REP_DAILY','insights','1','Consumo diario','/comedor/reportes/diario','8070'),
('8072','COMEDOR','REP_STUDENT','person','2','Por estudiante','/comedor/reportes/estudiante','8070'),
('8073','COMEDOR','REP_STATS','bar_chart','3','Estadísticas','/comedor/reportes/estadisticas','8070');