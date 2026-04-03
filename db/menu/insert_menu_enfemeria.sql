INSERT INTO menu (id, category, code, icon, menu_order, name, route, parent_id) VALUES

-- =========================
-- ROOT ENFERMERÍA
-- =========================
('6000','ENFERMERIA','ROOT','local_hospital','1','Enfermería',NULL,NULL),

-- =========================
-- DASHBOARD
-- =========================
('6001','ENFERMERIA','DASHBOARD','dashboard','1','Dashboard',NULL,'6000'),
('6002','ENFERMERIA','TODAY_ATTENTIONS','healing','1','Atenciones del día','/enfermeria/dashboard/atenciones','6001'),
('6003','ENFERMERIA','MEDICAL_ALERTS','warning','2','Alertas médicas','/enfermeria/dashboard/alertas','6001'),
('6004','ENFERMERIA','EMERGENCIES','emergency','3','Emergencias','/enfermeria/dashboard/emergencias','6001'),
('6005','ENFERMERIA','ACTIVITY','history','4','Actividad reciente','/enfermeria/dashboard/actividad','6001'),

-- =========================
-- FICHA MÉDICA
-- =========================
('6010','ENFERMERIA','MEDICAL_RECORD','folder_shared','2','Ficha Médica',NULL,'6000'),
('6011','ENFERMERIA','STUDENT_LIST','list','1','Lista de estudiantes','/enfermeria/ficha/lista','6010'),
('6012','ENFERMERIA','MEDICAL_DATA','assignment','2','Datos médicos','/enfermeria/ficha/datos','6010'),
('6013','ENFERMERIA','ALLERGIES','warning','3','Alergias','/enfermeria/ficha/alergias','6010'),
('6014','ENFERMERIA','DISEASES','coronavirus','4','Enfermedades','/enfermeria/ficha/enfermedades','6010'),
('6015','ENFERMERIA','MEDICATION','medication','5','Medicación','/enfermeria/ficha/medicacion','6010'),
('6016','ENFERMERIA','RESTRICTIONS','block','6','Restricciones','/enfermeria/ficha/restricciones','6010'),
('6017','ENFERMERIA','CLINICAL_HISTORY','history','7','Historial clínico','/enfermeria/ficha/historial','6010'),

-- =========================
-- ATENCIONES
-- =========================
('6020','ENFERMERIA','ATTENTIONS','healing','3','Atenciones',NULL,'6000'),
('6021','ENFERMERIA','ATTENTION_CREATE','add','1','Registrar atención','/enfermeria/atenciones/registrar','6020'),
('6022','ENFERMERIA','ATTENTION_TYPE','category','2','Tipo de atención','/enfermeria/atenciones/tipo','6020'),
('6023','ENFERMERIA','TREATMENT','medical_services','3','Tratamiento','/enfermeria/atenciones/tratamiento','6020'),
('6024','ENFERMERIA','ATTENTION_HISTORY','history','4','Historial','/enfermeria/atenciones/historial','6020'),

-- =========================
-- MEDICAMENTOS
-- =========================
('6030','ENFERMERIA','MEDICINES','medication','4','Medicamentos',NULL,'6000'),
('6031','ENFERMERIA','MED_REGISTER','playlist_add','1','Registro','/enfermeria/medicamentos/registro','6030'),
('6032','ENFERMERIA','DOSE_CONTROL','science','2','Control de dosis','/enfermeria/medicamentos/dosis','6030'),
('6033','ENFERMERIA','FREQUENCY','schedule','3','Frecuencia','/enfermeria/medicamentos/frecuencia','6030'),
('6034','ENFERMERIA','MED_HISTORY','history','4','Historial','/enfermeria/medicamentos/historial','6030'),
('6035','ENFERMERIA','STOCK','inventory','5','Stock','/enfermeria/medicamentos/stock','6030'),

-- =========================
-- EMERGENCIAS
-- =========================
('6040','ENFERMERIA','EMERGENCY_MODULE','emergency','5','Emergencias',NULL,'6000'),
('6041','ENFERMERIA','EMERGENCY_CREATE','add_alert','1','Registrar emergencia','/enfermeria/emergencias/registrar','6040'),
('6042','ENFERMERIA','SEVERITY','priority_high','2','Nivel de gravedad','/enfermeria/emergencias/gravedad','6040'),
('6043','ENFERMERIA','ACTIONS','build','3','Acciones','/enfermeria/emergencias/acciones','6040'),
('6044','ENFERMERIA','CONTACT_PARENT','call','4','Contacto apoderado','/enfermeria/emergencias/contacto','6040'),
('6045','ENFERMERIA','EMERGENCY_HISTORY','history','5','Historial','/enfermeria/emergencias/historial','6040'),

-- =========================
-- APODERADOS
-- =========================
('6050','ENFERMERIA','PARENTS','people','6','Apoderados',NULL,'6000'),
('6051','ENFERMERIA','PARENT_CONTACT','contact_phone','1','Contacto','/enfermeria/apoderados/contacto','6050'),
('6052','ENFERMERIA','EMERGENCY_CONTACT','contact_emergency','2','Emergencia','/enfermeria/apoderados/emergencia','6050'),

-- =========================
-- CALENDARIO MÉDICO
-- =========================
('6060','ENFERMERIA','MEDICAL_CALENDAR','calendar_month','7','Calendario / Controles',NULL,'6000'),
('6061','ENFERMERIA','MED_CHECKS','event_available','1','Controles médicos','/enfermeria/calendario/controles','6060'),
('6062','ENFERMERIA','VACCINES','vaccines','2','Vacunaciones','/enfermeria/calendario/vacunas','6060'),
('6063','ENFERMERIA','FOLLOW_UP','track_changes','3','Seguimiento','/enfermeria/calendario/seguimiento','6060'),
('6064','ENFERMERIA','APPOINTMENTS','event','4','Citas','/enfermeria/calendario/citas','6060'),
('6065','ENFERMERIA','HEALTH_CAMPAIGNS','campaign','5','Campañas de salud','/enfermeria/calendario/campanas','6060'),

-- =========================
-- ALERTAS MÉDICAS
-- =========================
('6070','ENFERMERIA','ALERTS_MODULE','warning','8','Alertas Médicas',NULL,'6000'),
('6071','ENFERMERIA','CRITICAL_ALLERGIES','report_problem','1','Alergias críticas','/enfermeria/alertas/alergias','6070'),
('6072','ENFERMERIA','CHRONIC_DISEASES','monitor_heart','2','Enfermedades crónicas','/enfermeria/alertas/enfermedades','6070'),
('6073','ENFERMERIA','MANDATORY_MED','medication','3','Medicación obligatoria','/enfermeria/alertas/medicacion','6070'),
('6074','ENFERMERIA','AUTO_ALERTS','notifications_active','4','Alertas automáticas','/enfermeria/alertas/automaticas','6070'),

-- =========================
-- REPORTES
-- =========================
('6080','ENFERMERIA','REPORTS','assessment','9','Reportes',NULL,'6000'),
('6081','ENFERMERIA','REP_ATTENTIONS','healing','1','Atenciones','/enfermeria/reportes/atenciones','6080'),
('6082','ENFERMERIA','REP_EMERGENCIES','emergency','2','Emergencias','/enfermeria/reportes/emergencias','6080'),
('6083','ENFERMERIA','REP_HISTORY','history','3','Historial médico','/enfermeria/reportes/historial','6080'),
('6084','ENFERMERIA','REP_MEDICINES','medication','4','Medicamentos','/enfermeria/reportes/medicamentos','6080'),
('6085','ENFERMERIA','REP_INDICATORS','insights','5','Indicadores','/enfermeria/reportes/indicadores','6080');