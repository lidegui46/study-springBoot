package ldg.study.springboot.designPattern.builder;

/**
 * 构建器模式
 *
 * @author： ldg
 * @create date： 2019/1/8
 */
public class BuilderTest {
    private String id;
    private String name;

    private BuilderTest() {
        //do nothing
    }

    /**
     * 内部静态类
     */
    public static class Builder {
        private BuilderTest builderTest = new BuilderTest();

        public Builder id(String id) {
            builderTest.id = id;
            return this;
        }

        public Builder name(String name) {
            builderTest.name = name;
            return this;
        }

        public BuilderTest build() {
            return builderTest;
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class BuilderTestA {

    public static void main(String[] args) {
        BuilderTest builderTest = new BuilderTest.Builder()
                .id("id")
                .name("name")
                .build();
        System.out.println(builderTest.getId());
    }
}