
multibranchPipelineJob('forjj-gitlab') {
  description('Folder for Project forjj-gitlab generated and maintained by Forjj. To update it use forjj update')
  branchSources {
      github {
          repoOwner('forj-oss')
          scanCredentialsId('github-user')
          repository('forjj-gitlab')
      }
  }
  orphanedItemStrategy {
      discardOldItems {
          numToKeep(20)
      }
  }
}
