package com.humanizer.rest;

import com.humanizer.TextHumanizer;
import com.humanizer.HumanizationResult;
import com.humanizer.rest.dto.HumanizeRequest;
import com.humanizer.rest.dto.HumanizeResponse;
import io.javalin.Javalin;
import java.util.HashMap;

public class HumanizerRestApp {
    public static void main(String[] args) {
        int port = 7000;
        Javalin app = Javalin.create().start(port);

        TextHumanizer humanizer = new TextHumanizer();

        app.post("/humanize", ctx -> {
            HumanizeRequest req = ctx.bodyAsClass(HumanizeRequest.class);
            if (req == null || req.getText() == null || req.getText().trim().isEmpty()) {
                ctx.status(400).result("{\"error\":\"text must be provided\"}");
                return;
            }

            HumanizationResult res = humanizer.humanize(req.getText());

            HumanizeResponse resp = new HumanizeResponse();
            resp.setOriginalText(res.getOriginalText());
            resp.setHumanizedText(res.getHumanizedText());
            resp.setHumanizationScore(res.getHumanizationScore());
            resp.setModifications(res.getModifications());
            resp.setAppliedRules(res.getAppliedRules());

            // try to copy metadata if available (implementation-specific)
            try {
                java.lang.reflect.Method m = res.getClass().getMethod("getMetadata", String.class);
                // skip: metadata is accessible via implementation; fallback to empty map
                resp.setMetadata(new HashMap<>());
            } catch (NoSuchMethodException e) {
                resp.setMetadata(new HashMap<>());
            }

            ctx.json(resp);
        });

        app.get("/health", ctx -> ctx.result("ok"));

        System.out.println("Humanizer REST API démarrée sur le port " + port);
    }
}
