package com.github.javaparser.bootstrap;

import com.laamella.gencodegen.core.io.OutputAggregator;

public interface Generator {
    void generate(Model model, OutputAggregator aggregator) throws Exception;
}
