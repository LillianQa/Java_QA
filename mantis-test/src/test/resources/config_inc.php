<?php
# --- Database Configuration ---
$g_hostname      = 'localhost';
$g_db_username   = 'mantisdbuser';
$g_db_password   = '';
$g_database_name = 'bugtracker';
$g_db_type       = 'mysql';
$g_signup_use_captcha = OFF;
# --- Email Configuration ---
$g_phpMailer_method		= PHPMAILER_METHOD_SMTP; # or PHPMAILER_METHOD_SMTP, PHPMAILER_METHOD_SENDMAIL
$g_smtp_host			= 'localhost';			# used with PHPMAILER_METHOD_SMTP
$g_smtp_username		= '';					# used with PHPMAILER_METHOD_SMTP
$g_smtp_password		= '';					# used with PHPMAILER_METHOD_SMTP
$g_from_name			= 'Mantis Bug Tracker';
$g_email_receive_own	= OFF;
$g_email_send_using_cronjob = OFF;