package com.senla.hotel.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.senla.hotel.model.Preorder;
import com.senla.hotel.service.api.IManagerMail;
import com.senla.hotel.util.Constant;

import freemarker.template.Configuration;

@SuppressWarnings("deprecation")
@Service("mailService")
public class ManagerMail implements IManagerMail {

	protected static final String ORDER = "order";

	public static Logger log = LogManager.getLogger(ManagerRoomService.class);

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	VelocityEngine velocityEngine;

	@Autowired
	Configuration freemarkerConfiguration;

	@Override
	public void sendEmail(Object object) {

		Preorder order = (Preorder) object;
		MimeMessagePreparator preparator = getMessagePreparator(order);

		try {
			mailSender.send(preparator);
		} catch (MailException ex) {
			log.error(ex.getMessage());
		}
	}

	private MimeMessagePreparator getMessagePreparator(final Preorder order) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setSubject(Constant.MAILSUBJ);
				helper.setFrom(Constant.MAILFROM);
				helper.setTo(order.getEmailGuest());
				Map<String, Object> model = new HashMap<String, Object>();
				model.put(ORDER, order);
				String text = geFreeMarkerTemplateContent(model);
				helper.setText(text, true);
				helper.addAttachment("logo.png", new ClassPathResource("head.png"));

			}

		};
		return preparator;
	}

	public String geVelocityTemplateContent(Map<String, Object> model) {
		StringBuffer content = new StringBuffer();
		try {
			content.append(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
					"/vmtemplates/velocity_mailTemplate.vm", model));
			return content.toString();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "";
	}

	public String geFreeMarkerTemplateContent(Map<String, Object> model) {
		StringBuffer content = new StringBuffer();
		try {
			content.append(FreeMarkerTemplateUtils
					.processTemplateIntoString(freemarkerConfiguration.getTemplate("fm_mailTemplate.txt"), model));
			return content.toString();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "";
	}

}
