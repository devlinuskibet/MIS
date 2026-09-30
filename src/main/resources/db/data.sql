-- Initial Seed Data for Church Management System

-- Seed Admin (Password: admin123 hashed using BCrypt)
INSERT INTO admin (email, password)
VALUES ('admin@church.org', '$2a$10$7Q78R11Z9xL6zXJz3K7u8.8lGz2R6y.1xQ2yW3e4r5t6y7u8i9o0')
ON CONFLICT (email) DO NOTHING;

-- Seed Sample Members
INSERT INTO member_registration (full_name, email, phone, dob, address, date_joined)
VALUES 
('John Doe', 'john.doe@example.com', '+1234567890', '1990-05-15', '123 Grace Street', '2024-01-10'),
('Jane Smith', 'jane.smith@example.com', '+1987654321', '1992-08-22', '456 Faith Avenue', '2024-02-01')
ON CONFLICT (email) DO NOTHING;

-- Seed Announcements
INSERT INTO announcement (announcement_type, title, message, effective_date, effective_until, target_audience)
VALUES 
('General', 'Sunday Worship Service', 'Join us this Sunday at 9:00 AM for a powerful worship session.', CURRENT_DATE, CURRENT_DATE + INTERVAL '7 days', 'All Members'),
('Youth', 'Youth Fellowship Gathering', 'Special youth seminar this Friday at 5:00 PM.', CURRENT_DATE, CURRENT_DATE + INTERVAL '5 days', 'Youth');

-- Seed Events
INSERT INTO event (title, location, description, date, time)
VALUES 
('Annual Thanksgiving Service', 'Main Sanctuary', 'A special Thanksgiving service for all families.', CURRENT_DATE + INTERVAL '14 days', '10:00:00');

-- Seed Sermons
INSERT INTO sermon (title, speaker, date, url, description)
VALUES 
('Walking in Grace', 'Pastor David', CURRENT_DATE, 'https://media.church.org/sermons/walking-in-grace.mp3', 'An uplifting sermon on living by faith and grace.');
