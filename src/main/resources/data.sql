INSERT INTO clients(name, rfc, fiscal_address)
VALUES ('ALEJANDRO JACOB MATOS SALAZAR', 'MASA961112F68', '8 #231 Vista Alegre Norte');

INSERT INTO projects(project_name, address, resident)
VALUES ('AMPLIACION CASA MERIDA', '8 #231 Vista Alegre Norte, Merida, Yucatan 97130', 'MIRIAM GUADALUPE MATOS SALAZAR');

INSERT INTO concrete_work_orders(sample_date, service_hour, resistance_fc_kgf_cm2,
concrete_slump_cm, concrete_volume_m3, tma_mm, design_age_days, concrete_provider)
VALUES ('2023-03-07', '13:00', 250, 14, 7, 10, 28, 'EPICO CONCRETOS');

INSERT INTO client_projects(client_id, project_id)
VALUES (1, 1);

INSERT INTO project_work_orders(project_id,concrete_work_order_id)
VALUES (1, 1);