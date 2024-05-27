import { Avatar, Box, Text, VStack } from '@chakra-ui/react';

const UserList = ({ users, handleFunc = () => {} }) => {
    return (
        <Box>
            {users.map((user) => (
                <Box
                    key={user._id}
                    display="flex"
                    alignItems="center"
                    p="4"
                    mb="4"
                    borderRadius="md"
                    borderWidth="1px"
                    borderColor="gray.200"
                    _hover={{ background: '#38B2AC', color: 'white', cursor: 'pointer' }}
                    boxShadow="lg"
                    rounded="md"
                    bg="white"
                    onClick={() => handleFunc(user)}
                >
                    <Avatar src={user.picture} boxSize="50px" borderRadius="full" mr="4" />
                    <VStack align="start" spacing="1">
                        <Text fontWeight="bold">{user.name}</Text>
                        <Text fontSize="sm">{user.email}</Text>
                    </VStack>
                </Box>
            ))}
        </Box>
    );
};

export default UserList;
