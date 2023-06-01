package common.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler { // 앞으로 만들 핸들러의 기본 틀
public String process(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
