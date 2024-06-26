import Joi from 'joi';

const userValidate = (data) => {
    const userSchema = Joi.object({
        name: Joi.string(),
        email: Joi.string()
            .email({ tlds: { allow: false } })
            .pattern(new RegExp('gmail.com|ou.edu.vn'))
            .lowercase()
            .required(),
        password: Joi.string().min(6).max(30).required(),
        picture: Joi.string().uri().allow(null),
        role: Joi.string().allow('user', 'manager', 'admin').default('user'),
    });
    //validate
    return userSchema.validate(data);
};

export { userValidate };
