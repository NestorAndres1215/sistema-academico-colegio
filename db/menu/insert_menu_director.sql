INSERT INTO menu (id, category, code, icon, menu_order, name, route, parent_id) VALUES

-- =========================
-- ROOT DIRECTOR
-- =========================
('3000','DIRECTOR','ROOT','manage_accounts','1','Director',NULL,NULL),

-- =========================
-- DASHBOARD
-- =========================
('3001','DIRECTOR','DASHBOARD','dashboard','1','Dashboard',NULL,'3000'),
('3002','DIRECTOR','KPIS','insights','1','KPIs generales','/director/kpis','3001'),
('3003','DIRECTOR','ACTIVITY','history','2','Actividad reciente','/director/actividad','3001'),
('3004','DIRECTOR','ALERTS','warning','3','Alertas críticas','/director/alertas','3001'),
('3005','DIRECTOR','AGENDA_DAY','today','4','Agenda del día','/director/agenda-dia','3001'),

-- =========================
-- AGENDA INSTITUCIONAL
-- =========================
('3010','DIRECTOR','AGENDA','calendar_month','2','Agenda Institucional',NULL,'3000'),

('3011','DIRECTOR','CALENDAR','calendar_view_month','1','Vista calendario',NULL,'3010'),
('3012','DIRECTOR','DAILY','view_day','1','Diario','/director/calendario/diario','3011'),
('3013','DIRECTOR','WEEKLY','view_week','2','Semanal','/director/calendario/semanal','3011'),
('3014','DIRECTOR','MONTHLY','calendar_month','3','Mensual','/director/calendario/mensual','3011'),

('3015','DIRECTOR','EVENTS','event','2','Eventos',NULL,'3010'),
('3016','DIRECTOR','INST_EVENTS','account_balance','1','Eventos institucionales','/director/eventos/institucionales','3015'),
('3017','DIRECTOR','ACTIVITIES','celebration','2','Actividades escolares','/director/eventos/actividades','3015'),
('3018','DIRECTOR','WORKSHOPS','groups','3','Talleres','/director/eventos/talleres','3015'),

('3019','DIRECTOR','EVALUATIONS','grading','3','Evaluaciones',NULL,'3010'),
('3020','DIRECTOR','SCHEDULE','schedule','1','Cronograma académico','/director/evaluaciones','3019'),

('3021','DIRECTOR','MEETINGS','groups','4','Reuniones',NULL,'3010'),
('3022','DIRECTOR','PARENT_MEET','groups','1','Reuniones con padres','/director/reuniones/padres','3021'),
('3023','DIRECTOR','INTERNAL_MEET','domain','2','Reuniones internas','/director/reuniones/internas','3021'),

('3024','DIRECTOR','VISITS','badge','5','Visitas',NULL,'3010'),
('3025','DIRECTOR','VISIT_PLAN','event_available','1','Visitas programadas','/director/visitas','3024'),
('3026','DIRECTOR','VISIT_HISTORY','history','2','Historial','/director/visitas/historial','3024'),

('3027','DIRECTOR','REMINDERS','notifications','6','Recordatorios',NULL,'3010'),
('3028','DIRECTOR','EVENT_ALERT','notification_important','1','Alertas de eventos','/director/recordatorios/eventos','3027'),
('3029','DIRECTOR','TASKS','assignment','2','Tareas importantes','/director/recordatorios/tareas','3027'),

-- =========================
-- SUPERVISION ACADEMICA
-- =========================
('3030','DIRECTOR','ACADEMIC','school','3','Supervisión Académica',NULL,'3000'),
('3031','DIRECTOR','PERFORMANCE','insights','1','Rendimiento académico','/director/academico/rendimiento','3030'),
('3032','DIRECTOR','ATTENDANCE','fact_check','2','Asistencia general','/director/academico/asistencia','3030'),
('3033','DIRECTOR','RISK','warning','3','Estudiantes en riesgo','/director/academico/riesgo','3030'),
('3034','DIRECTOR','EVOLUTION','trending_up','4','Evolución','/director/academico/evolucion','3030'),

('3035','DIRECTOR','WORKSHOP_SUP','groups','5','Talleres',NULL,'3030'),
('3036','DIRECTOR','PARTICIPATION','bar_chart','1','Participación','/director/talleres/participacion','3035'),
('3037','DIRECTOR','PERF_WORK','insights','2','Desempeño','/director/talleres/desempeno','3035'),

-- =========================
-- GESTION PERSONAL
-- =========================
('3040','DIRECTOR','STAFF','badge','4','Gestión de Personal',NULL,'3000'),

('3041','DIRECTOR','TEACHERS','school','1','Profesores',NULL,'3040'),
('3042','DIRECTOR','TEACHER_LIST','list','1','Lista','/director/profesores','3041'),
('3043','DIRECTOR','TEACHER_PROFILE','person','2','Perfil','/director/profesores/perfil','3041'),

('3044','DIRECTOR','TEACHER_SUP','insights','3','Supervisión',NULL,'3041'),
('3045','DIRECTOR','TEACHER_PERF','bar_chart','1','Desempeño','/director/profesores/desempeno','3044'),
('3046','DIRECTOR','TEACHER_ATT','fact_check','2','Asistencia','/director/profesores/asistencia','3044'),
('3047','DIRECTOR','TEACHER_COURSES','menu_book','3','Cursos asignados','/director/profesores/cursos','3044'),

('3048','DIRECTOR','INTERVENTION','build','4','Intervención',NULL,'3041'),
('3049','DIRECTOR','ASSIGN','swap_horiz','1','Asignar cursos','/director/profesores/asignar','3048'),
('3050','DIRECTOR','REASSIGN','autorenew','2','Reasignar','/director/profesores/reasignar','3048'),
('3051','DIRECTOR','ASSIGN_WORK','groups','3','Asignar a talleres','/director/profesores/taller','3048'),
('3052','DIRECTOR','REMOVE_ASSIGN','remove_circle','4','Quitar asignación','/director/profesores/quitar','3048'),

('3053','DIRECTOR','LOAD','schedule','5','Carga docente',NULL,'3041'),
('3054','DIRECTOR','HOURS','schedule','1','Horas asignadas','/director/profesores/horas','3053'),
('3055','DIRECTOR','AVAIL','event_available','2','Disponibilidad','/director/profesores/disponibilidad','3053'),

('3056','DIRECTOR','ALERTS_TEACH','warning','6','Alertas',NULL,'3041'),
('3057','DIRECTOR','NO_GRADES','error','1','Falta de notas','/director/profesores/alertas/notas','3056'),
('3058','DIRECTOR','ABSENCE','event_busy','2','Inasistencias','/director/profesores/alertas/asistencia','3056'),
('3059','DIRECTOR','LOW_PERF','trending_down','3','Bajo desempeño','/director/profesores/alertas/desempeno','3056'),

-- =========================
-- INFRAESTRUCTURA
-- =========================
('3060','DIRECTOR','INFRA','apartment','6','Infraestructura y Recursos',NULL,'3000'),
('3061','DIRECTOR','INFRASTRUCTURE','domain','1','Infraestructura','/director/infraestructura','3060'),
('3062','DIRECTOR','MAINTENANCE','build_circle','2','Mantenimiento','/director/mantenimiento','3060'),
('3063','DIRECTOR','INVENTORY','inventory','3','Inventario','/director/inventario','3060'),
('3064','DIRECTOR','INFRA_ALERT','warning','4','Alertas','/director/infra/alertas','3060'),

-- =========================
-- VIDA INSTITUCIONAL
-- =========================
('3070','DIRECTOR','INSTITUTION','event','7','Vida Institucional',NULL,'3000'),
('3071','DIRECTOR','EVENTS_INST','event_available','1','Eventos','/director/institucional/eventos','3070'),
('3072','DIRECTOR','MEETINGS_INST','groups','2','Reuniones','/director/institucional/reuniones','3070'),
('3073','DIRECTOR','VISITS_INST','badge','3','Visitas','/director/institucional/visitas','3070'),
('3074','DIRECTOR','AGREEMENTS','handshake','4','Convenios','/director/institucional/convenios','3070'),

-- =========================
-- FINANZAS
-- =========================
('3080','DIRECTOR','FINANCE','payments','8','Finanzas',NULL,'3000'),
('3081','DIRECTOR','INCOME','trending_up','1','Ingresos','/director/finanzas/ingresos','3080'),
('3082','DIRECTOR','DEBTS','money_off','2','Deudas','/director/finanzas/deudas','3080'),
('3083','DIRECTOR','CASH','point_of_sale','3','Caja','/director/finanzas/caja','3080'),
('3084','DIRECTOR','DINING','restaurant','4','Comedor','/director/finanzas/comedor','3080'),
('3085','DIRECTOR','FIN_IND','insights','5','Indicadores financieros','/director/finanzas/indicadores','3080'),

-- =========================
-- ANALITICA
-- =========================
('3090','DIRECTOR','ANALYTICS','analytics','9','Analítica',NULL,'3000'),
('3091','DIRECTOR','DASH_ANALYTIC','dashboard','1','Dashboard','/director/analitica','3090'),
('3092','DIRECTOR','COMPARE','compare','2','Comparativas','/director/analitica/comparativas','3090'),
('3093','DIRECTOR','TRENDS','trending_up','3','Tendencias','/director/analitica/tendencias','3090'),
('3094','DIRECTOR','ADVANCED','insights','4','Indicadores avanzados','/director/analitica/avanzado','3090'),

-- =========================
-- REPORTES
-- =========================
('3100','DIRECTOR','REPORTS','assessment','10','Reportes',NULL,'3000'),
('3101','DIRECTOR','REP_ACAD','school','1','Académico','/director/reportes/academico','3100'),
('3102','DIRECTOR','REP_ATT','fact_check','2','Asistencia','/director/reportes/asistencia','3100'),
('3103','DIRECTOR','REP_DISC','gavel','3','Disciplina','/director/reportes/disciplina','3100'),
('3104','DIRECTOR','REP_FIN','payments','4','Financiero','/director/reportes/financiero','3100'),
('3105','DIRECTOR','REP_INFRA','apartment','5','Infraestructura','/director/reportes/infra','3100'),
('3106','DIRECTOR','EXPORT','download','6','Exportaciones','/director/reportes/exportar','3100'),

-- =========================
-- CONFIG
-- =========================
('3110','DIRECTOR','CONFIG','settings','11','Configuración Estratégica',NULL,'3000'),
('3111','DIRECTOR','YEAR','calendar_today','1','Año académico','/director/config/anio','3110'),
('3112','DIRECTOR','PARAMS','tune','2','Parámetros','/director/config/parametros','3110'),
('3113','DIRECTOR','FLOWS','alt_route','3','Flujos','/director/config/flujos','3110'),
('3114','DIRECTOR','ALERT_CONFIG','notifications','4','Configuración de alertas','/director/config/alertas','3110'),
('3115','DIRECTOR','POLICIES','gavel','5','Políticas institucionales','/director/config/politicas','3110');